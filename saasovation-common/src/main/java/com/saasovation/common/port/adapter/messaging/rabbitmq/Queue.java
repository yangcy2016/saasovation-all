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
    private Exchange exchange;
    private String routeKey;

    private Queue(Exchange exchange,String queueName){
       this(exchange,queueName,"*");
    }

    private Queue(Exchange exchange,String queueName,String routeKey){
        this.exchange = exchange;
        this.queueName = queueName;
        this.routeKey = routeKey;
    }

    public static Queue individualExchangeSubscriberInstance(Exchange exchange, String queueName) {
        return new Queue(exchange,queueName);
    }

    public static Queue individualExchangeSubscriberInstance(Exchange exchange, String queueName,String routeKey) {
        return new Queue(exchange,queueName,routeKey);
    }

    public QueueingConsumer queueingConsumer(){
        if(queueingConsumer==null){
            setQueueingConsumer(exchange.queueingConsumer(queueName,routeKey));
        }
        return this.queueingConsumer;
    }
    public String queueName(){
        return queueName;
    }

    public Exchange exchange(){
        return this.exchange;
    }

    private void setQueueingConsumer(QueueingConsumer queueingConsumer){
        this.queueingConsumer = queueingConsumer;
    }
}
