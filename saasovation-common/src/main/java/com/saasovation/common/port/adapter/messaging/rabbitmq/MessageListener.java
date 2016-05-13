package com.saasovation.common.port.adapter.messaging.rabbitmq;

import java.util.Date;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 2:53
 * @since : ${VERSION}
 */
public abstract class MessageListener<T> {

    public MessageListener() {

    }

    public abstract void handleMessage(String type,String messageId,Date timestamp,
                              T textMessage,long deliveryTag,boolean isRedelivery);


}
