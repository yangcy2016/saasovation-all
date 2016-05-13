package com.saasovation.common.port.adapter.messaging.rabbitmq;

/**
 * @author : huanghy
 * @create : 2016/5/13 0013 上午 10:55
 * @since : ${VERSION}
 */
public class MessageDecoderException extends RuntimeException {
    public MessageDecoderException() {
        super();
    }

    public MessageDecoderException(String message) {
        super(message);
    }

    public MessageDecoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageDecoderException(Throwable cause) {
        super(cause);
    }

    protected MessageDecoderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
