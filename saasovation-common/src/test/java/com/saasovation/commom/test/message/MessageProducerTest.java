package com.saasovation.commom.test.message;

import com.saasovation.common.port.adapter.messaging.rabbitmq.ConnectionSettings;
import com.saasovation.common.port.adapter.messaging.rabbitmq.Exchange;
import com.saasovation.common.port.adapter.messaging.rabbitmq.MessageParameters;
import com.saasovation.common.port.adapter.messaging.rabbitmq.MessageProducer;

import java.util.Date;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 5:28
 * @since : ${VERSION}
 */
public class MessageProducerTest {
    public static void main(String[] args) {
        Exchange exchange = Exchange.fanOutInstance(ConnectionSettings.instance(),"ddd_test",true);
        MessageProducer producer = new MessageProducer(exchange);
        MessageParameters parameters = MessageParameters.durableTextParameters("test",System.currentTimeMillis()+"",new Date());
        producer.send("helloworld",parameters);
        producer.close();
    }
}
