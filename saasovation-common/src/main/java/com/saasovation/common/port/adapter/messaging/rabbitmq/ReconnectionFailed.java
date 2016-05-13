package com.saasovation.common.port.adapter.messaging.rabbitmq;

/**
 * @author : huanghy
 * @create : 2016/5/13 0013 下午 12:32
 * @since : ${VERSION}
 */
public class ReconnectionFailed extends RuntimeException {
    public ReconnectionFailed() {
        super();
    }

    public ReconnectionFailed(String message) {
        super(message);
    }

    public ReconnectionFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public ReconnectionFailed(Throwable cause) {
        super(cause);
    }

    protected ReconnectionFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
