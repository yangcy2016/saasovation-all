package com.saasovation.common.port.adapter.messaging.rabbitmq;

/**
 * @author : huanghy
 * @create : 2016/5/13 0013 上午 10:51
 * @since : ${VERSION}
 */
public interface Decoder<T> {
    T decode(byte[] body);
}
