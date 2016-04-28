package com.saasovation.common.port.adapter.messaging.rabbitmq;

import com.rabbitmq.client.QueueingConsumer;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 2:48
 * @since : ${VERSION}
 */
public class Queue {
    private QueueingConsumer queueingConsumer;
    private String queueName;

    private Queue(QueueingConsumer consumer,String queueName){
        this.queueingConsumer = consumer;
        this.queueName = queueName;
    }

    public static Queue individualExchangeSubscriberInstance(Exchange exchange, String queueName) {
        QueueingConsumer consumer = exchange.bindQueue(queueName, "*");
        return new Queue(consumer,queueName);
    }

    public static Queue individualExchangeSubscriberInstance(Exchange exchange, String queueName,String routeKey) {
        QueueingConsumer consumer = exchange.bindQueue(queueName, routeKey);
        return new Queue(consumer,queueName);
    }

    public QueueingConsumer queueingConsumer(){
        return this.queueingConsumer;
    }
    public String queueName(){
        return queueName;
    }
}
