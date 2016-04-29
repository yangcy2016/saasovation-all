package com.saasovation.common.port.adapter.messaging.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class Exchange {
	private Connection connection;
	private Channel channel;
	private String exchangeName;

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

	public Exchange(Connection connection, Channel channel,String exchangeName) {
		super();
		this.connection = connection;
		this.channel = channel;
		this.exchangeName = exchangeName;
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
			channel.exchangeDeclare(exchangeName,type,durable);
			exchange = new Exchange(connection,channel,exchangeName);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		return exchange;
	}

	private static Channel initChannel(Connection connection) throws Exception{
		Channel channel = connection.createChannel();
		return channel;
	}

	private static Connection connect(ConnectionSettings settings) throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(settings.getHost());
		factory.setPort(settings.getPort());
		factory.setUsername(settings.getUserName());
		factory.setPassword(settings.getPassword());
		Connection connection = factory.newConnection();
		return connection;
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


	public QueueingConsumer bindQueue(String queueName, String routKey){
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
