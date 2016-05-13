package com.saasovation.common.port.adapter.messaging.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.DefaultExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Exchange {
	private Connection connection;
	private Channel channel;
	private String exchangeName;

	private static int retry = 5;

	private  ConnectionSettings settings;

	private static final Logger logger = LoggerFactory.getLogger(Exchange.class);

	public enum ExchangeType{
		FANOUT("fanout"),
		DIRECT("direct"),
		TOPIC("topic");
		private String type;
		ExchangeType(String type) {
			this.type = type;
		}
		public String getType(){
			return this.type;
		}
	}

	private Exchange(Connection connection, Channel channel,String exchangeName,ConnectionSettings settings) {
		super();
		setConnection(connection);
		setChannel(channel);
		this.exchangeName = exchangeName;
		this.settings = settings;
	}
	public static Exchange fanOutInstance(ConnectionSettings settings,String exchangeName,boolean durable){
		return createInstance(settings,exchangeName,durable,ExchangeType.FANOUT.getType());
	}

	public static Exchange directInstance(ConnectionSettings settings,String exchangeName,boolean durable){
		return createInstance(settings,exchangeName,durable,ExchangeType.DIRECT.getType());
	}
	public static Exchange topicInstance(ConnectionSettings settings,String exchangeName,boolean durable){
		return createInstance(settings,exchangeName,durable,ExchangeType.TOPIC.getType());
	}

	private static Exchange createInstance(ConnectionSettings settings,String exchangeName,boolean durable,String type){
		Exchange exchange = null;
		try {
			Connection connection = connect(settings);
			Channel channel = initChannel(connection);
			channel.exchangeDeclare(exchangeName, type, durable);
			exchange = new Exchange(connection,channel,exchangeName,settings);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		return exchange;
	}



	public void reconnect(){
		logger.info("reconnect to mq server:{},{}",settings.getHost(),settings.getPort());
		try {
			Connection newConnection = connect(settings);
			Channel newChannel = initChannel(newConnection);
			replaceWith(newConnection,newChannel);
		}catch (Exception e){
			throw new ReconnectionFailed(e);
		}
	}

	private void replaceWith(Connection conn, Channel channel){
		setConnection(conn);
		setChannel(channel);
	}

	private void setConnection(Connection connection){
		this.connection = connection;
	}

	private void setChannel(Channel channel){
		this.channel = channel;
	}

	private static Channel initChannel(Connection connection) throws Exception{
		Channel channel = connection.createChannel();
		return channel;
	}

	private static Connection connect(ConnectionSettings settings) throws Exception{
		Connection connection = null;
		try{
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(settings.getHost());
			factory.setPort(settings.getPort());
			factory.setUsername(settings.getUserName());
			factory.setPassword(settings.getPassword());
			factory.setExceptionHandler(new DefaultExceptionHandler());
			factory.setRequestedHeartbeat(settings.getHeadBeat());
			connection = factory.newConnection();
			resetRetry(retry);
		}catch (Exception e){
			logger.error("can't connect to rabbitmq server");
			if(retry-->0){
				logger.info("retry to connect to rabbit mq server {}",retry);
				TimeUnit.SECONDS.sleep(settings.getRetryInterval());
				connection = connect(settings);
			}else {
				throw new ReconnectionFailed(e);
			}
		}
		return connection;
	}

	private static void resetRetry(int cnt){
		retry = cnt;
	}
	
	public void call(String message,String routeKey) throws IOException{
		channel.basicPublish(
				exchangeName,
				routeKey,
				createTextRequestProperties(),
				message.getBytes());
	}
	
	public void call(String message,BasicProperties properties) throws IOException{
		channel.basicPublish(
				exchangeName, 
				"",
				properties,
				message.getBytes());
	}
	public void call(String message,BasicProperties properties,String routeKey) throws IOException{
		logger.info("send message to {} msg:{}",exchangeName,message);
		channel.basicPublish(
				exchangeName,
				routeKey,
				properties,
				message.getBytes());
	}
	public BasicProperties createTextRequestProperties(){
		return new BasicProperties.Builder()
				.contentType("application/json")
				.contentEncoding("UTF-8")
				.build();
	}


	public QueueingConsumer queueingConsumer(String queueName, String routKey){
		QueueingConsumer consumer = null;
		try {
			channel.queueDeclare(queueName,false,false,false,null);
			channel.queueBind(queueName, exchangeName, routKey);
			logger.info("{} queue bind to {} with route key {}",queueName,exchangeName,routKey);
			consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName,true,queueName,consumer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return consumer;
	}

	public <T> void defaultConsumer(MessageListener<T> listener,Decoder<T> decoder,String queueName) throws Exception {
		try {
			channel.basicConsume(queueName,true,new DefaultConsumer(channel){
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope,
										   BasicProperties properties, byte[] body) throws IOException {
					String 	type      	= properties.getType();
					String 	messageId 	= properties.getMessageId();
					Date   	occurredOn 	= properties.getTimestamp();
					T      	message    	= decoder.decode(body);
					long   	deliveryTag 	= envelope.getDeliveryTag();
					boolean isRedeliver 	= envelope.isRedeliver();

					listener.handleMessage(
							type,
							messageId,
							occurredOn,
							message,
							deliveryTag,
							isRedeliver);
				}
			});
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> void defaultConsumerWithRetry(MessageListener<T> listener,Decoder<T> decoder,String queueName) throws Exception {
		try {
			channel.basicConsume(queueName,true,new DefaultConsumer(channel){
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope,
										   BasicProperties properties, byte[] body) throws IOException {
					String type = properties.getType();
					String messageId = properties.getMessageId();
					Date occurredOn = properties.getTimestamp();
					T message = decoder.decode(body);
					long deliveryTag = envelope.getDeliveryTag();
					boolean isRedeliver = envelope.isRedeliver();
					listener.handleMessage(
							type,
							messageId,
							occurredOn,
							message,
							deliveryTag,
							isRedeliver);
				}

				@Override
				public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
					reconnect();
				}
			});
		} catch (Exception e) {
			throw e;
		}
	}



	public void close(){
		try {
			this.connection.close();
			this.connection = null;
			this.channel = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
