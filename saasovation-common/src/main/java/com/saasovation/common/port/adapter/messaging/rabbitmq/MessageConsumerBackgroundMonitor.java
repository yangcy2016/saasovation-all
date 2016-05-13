package com.saasovation.common.port.adapter.messaging.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : huanghy
 * @create : 2016/5/11 0011 上午 10:06
 * @since : ${VERSION}
 */
public class MessageConsumerBackgroundMonitor implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumerBackgroundMonitor.class);
    @Override
    public void run() {
        logger.info("run background monitor");
    }

    public void start(){
        new Thread(this,"MessageConsumerBackgroundMonitor").start();
    }
}
