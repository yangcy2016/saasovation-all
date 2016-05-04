package com.saasovation.common.port.adapter.messaging.rabbitmq;

import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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

    private MessageConsumer(Queue queue) {
        this(queue,"msg-consumer-"+cnt.incrementAndGet());
    }

    private MessageConsumer(Queue queue,String name) {
        this.name = name;
        this.queue = queue;
    }

    public static MessageConsumer instance(Queue queue, boolean b) {
        return new MessageConsumer(queue);
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

    private String name(){
        return this.name;
    }

    public void run(){
        logger.info("Wait for receive message ...");
        while (!stop){
            QueueingConsumer.Delivery delivery;
            try {
                delivery = queue.queueingConsumer().nextDelivery();
                String type      = delivery.getProperties().getType();
                if(filterBy(type)){
                    String messageId = delivery.getProperties().getMessageId();
                    long   deliveryTag = delivery.getEnvelope().getDeliveryTag();
                    String textMessage = getTextResponse(delivery);
                    Date   occurredOn  = delivery.getProperties().getTimestamp();
                    messageListener.handleMessage(
                            type,
                            messageId,
                            occurredOn,
                            textMessage,
                            deliveryTag,
                            false);
                }else{
                   logger.info("type:"+type+" not match ignored");
                }
            }catch (ShutdownSignalException soe){
                logger.error("connect error cause by {},consumer will shutdown",soe.getMessage());
                stop();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public boolean filterBy(String type){
        for(String listenType : listensToEvents){
            if(listenType.equals(type)){
                return true;
            }
        }
        return false;
    }

    public String getTextResponse(QueueingConsumer.Delivery delivery) throws UnsupportedEncodingException {
        return new String(delivery.getBody(),"UTF-8");
    }

    public void stop(){
        this.stop = true;
    }
}
