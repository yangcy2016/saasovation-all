package com.saasovation.common.port.adapter.messaging.rabbitmq;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 下午 1:18
 * @since : ${VERSION}
 */
public abstract class AutomaticExchangeListener extends ExchangeListener{

    public AutomaticExchangeListener() {
        attachToQueue();
        registerConsumer();
    }

    public abstract String exchangeName();

    private String queueName(){
        return getClass().getSimpleName();
    }

    public void attachToQueue(){
        Exchange exchange = Exchange.topicInstance(ConnectionSettings.instance(), exchangeName(), true);
        attachToQueue(exchange,exchangeName()+"."+queueName(),routeKey());
    }

    public abstract String routeKey();
}
