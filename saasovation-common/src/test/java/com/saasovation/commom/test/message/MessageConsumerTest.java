package com.saasovation.commom.test.message;

import com.saasovation.common.port.adapter.messaging.rabbitmq.ExchangeListener;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 5:38
 * @since : ${VERSION}
 */
public class MessageConsumerTest {
    public static void main(String[] args) {
        ExchangeListener listener = new ExchangeListener(){


            @Override
            protected void filteredDispatch(String type, String textMessage) {
                System.out.println("receive:"+textMessage);
            }

            @Override
            protected String[] listensToEvents() {
                return new String[0];
            }
        };
        listener.registerConsumer();
    }
}
