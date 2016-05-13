package com.saasovation.common.port.adapter.messaging.rabbitmq;

import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 2:40
 * @since : ${VERSION}
 */
public class MessageConsumer implements Runnable{
    private Queue queue;
    private volatile boolean stop = false;
    private String[] listensToEvents;
    private MessageListener messageListener;
    private String name;
    private static AtomicInteger cnt = new AtomicInteger(0);

    private ExchangeListener exchangeListener;

    private MessageConsumer(Queue queue) {
        this(queue,"msg-consumer-"+cnt.incrementAndGet());
    }

    private MessageConsumer(Queue queue,String name) {
        this.name = name;
        this.queue = queue;
    }

    private MessageConsumer(Queue queue,String name,ExchangeListener exchangeListener) {
        this.name = name;
        this.queue = queue;
        this.exchangeListener = exchangeListener;
    }

    public static MessageConsumer instance(Queue queue, boolean b,ExchangeListener listener) {
        return new MessageConsumer(queue,"msg-consumer-"+cnt.incrementAndGet(),listener);
    }

    public static MessageConsumer instance(Queue queue, boolean b,String name) {
        return new MessageConsumer(queue,name);
    }

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    public void receiveOnly(String[] listensToEvents, MessageListener messageListener) {
        Assert.notNull(listensToEvents, "listensToEvents must not be null");
        Assert.notNull(listensToEvents, "messageListener must not be null");
        this.listensToEvents = listensToEvents;
        this.messageListener = messageListener;
        new Thread(this,name()).start();
        logger.info("start message consumer {} to {}",name(),queue.queueName());
    }

    public void receiveOnly(String[] listensToEvents, MessageListener messageListener,ExecutorService executor) {
        Assert.notNull(listensToEvents, "listensToEvents must not be null");
        Assert.notNull(listensToEvents, "messageListener must not be null");
        this.listensToEvents = listensToEvents;
        this.messageListener = messageListener;
        executor.execute(this);
        logger.info("start message consumer {} to {}",name(),queue.queueName());
    }

    private String name(){
        return this.name;
    }

    private  void looConsumer(){
        QueueingConsumer.Delivery delivery;
        while (!stop){
            try {
                delivery = queue.queueingConsumer().nextDelivery();
                String type      = delivery.getProperties().getType();
                if(filterBy(type)){
                    String messageId = delivery.getProperties().getMessageId();
                    long   deliveryTag = delivery.getEnvelope().getDeliveryTag();
                    String textMessage = new TextDecoder().decode(delivery.getBody());
                    Date   occurredOn  = delivery.getProperties().getTimestamp();
                    boolean isRedelivery = delivery.getEnvelope().isRedeliver();
                    messageListener.handleMessage(
                            type,
                            messageId,
                            occurredOn,
                            textMessage,
                            deliveryTag,
                            isRedelivery);
                }else{
                    logger.info("type:"+type+" not match ignored");
                }
            }catch (ShutdownSignalException soe){
                try {
                    queue.exchange().reconnect();
                } catch (Exception e) {
                    logger.error("connect error cause by {},consumer will shutdown",soe.getMessage());
                    stop();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void loopConsumer1(){
        while (!stop){
            Exchange exchange = queue.exchange();
            try {
                exchange.defaultConsumerWithRetry(
                        messageListener,
                        new TextDecoder(),
                        queue.queueName()
                );
            }catch (ReconnectionFailed failed){
                logger.error("connect error cause by {},consumer will shutdown",failed.getMessage());
                stop();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void run(){
        logger.info("Wait for receive message ...");
        looConsumer();
    }

    public boolean filterBy(String type){
        for(String listenType : listensToEvents){
            if(listenType.equals(type)){
                return true;
            }
        }
        return false;
    }

    public void stop(){
        this.stop = true;
    }
}
