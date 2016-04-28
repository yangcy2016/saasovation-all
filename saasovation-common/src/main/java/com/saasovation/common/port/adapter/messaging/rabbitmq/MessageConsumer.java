package com.saasovation.common.port.adapter.messaging.rabbitmq;

import com.rabbitmq.client.QueueingConsumer;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 2:40
 * @since : ${VERSION}
 */
public class MessageConsumer {
    private Queue queue;
    private MessageConsumer(Queue queue){
        this.queue = queue;
    }
    public static MessageConsumer instance(Queue queue, boolean b) {
        return new MessageConsumer(queue);
    }

    public void receiveOnly(String[] listensToEvents, MessageListener messageListener) {
        new ConsumerTask(queue.queueingConsumer(),messageListener,listensToEvents).start();
    }


    private class ConsumerTask implements Runnable{
        private QueueingConsumer consumer;
        private MessageListener listener;
        private String[] listensToEvents;
        public ConsumerTask(QueueingConsumer consumer,MessageListener listener,String[] listensToEvents) {
            this.consumer = consumer;
            this.listener = listener;
            this.listensToEvents = listensToEvents;
        }

        @Override
        public void run() {
            System.out.println("Wait for receive message ...");
            while (true){
                QueueingConsumer.Delivery delivery;
                try {
                    delivery = consumer.nextDelivery();
                    String type      = delivery.getProperties().getType();
                    if(filterBy(type)){
                        String messageId = delivery.getProperties().getMessageId();
                        long   deliveryTag = 0L;//delivery.getProperties().getDeliveryMode();
                        String textMessage = getTextResponce(delivery);
                        Date   occurredOn  = delivery.getProperties().getTimestamp();
                        System.out.println(textMessage);
                        listener.handleMessage(
                                type,
                                messageId,
                                occurredOn,
                                textMessage,
                                deliveryTag,
                                false);
                    }else{
                        System.out.println("type:"+type+" not match ignored");
                    }
                }catch (Exception e){
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

        public String getTextResponce(QueueingConsumer.Delivery delivery) throws UnsupportedEncodingException {
            return new String(delivery.getBody(),"UTF-8");
        }

        public void start(){
            new Thread(this,"message-consumer").start();
        }
    }
}
