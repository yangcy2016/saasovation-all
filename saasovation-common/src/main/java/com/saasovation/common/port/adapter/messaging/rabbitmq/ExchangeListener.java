package com.saasovation.common.port.adapter.messaging.rabbitmq;

import java.util.Date;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 2:39
 * @since : ${VERSION}
 */
public abstract class ExchangeListener {
    private MessageConsumer messageConsumer;
    private Queue queue;

    public ExchangeListener() {

    }
    /**
     * 根据指定的类型进行消息处理 不满足的类型将被忽略
     * @param type 待处理的事件类型
     * @param textMessage
     */
    protected abstract void filteredDispatch(String type,String textMessage);

    /**
     * 指定需要进行处理的消息类型
     * @return
     */
    protected abstract String[] listensToEvents();

    /**
     *附加到需要监听的队列
     * @param exchange
     * @param queueName 队列名
     * @param routeKey
     */
    public void attachToQueue(Exchange exchange,String queueName,String routeKey){
        this.queue = Queue.individualExchangeSubscriberInstance(exchange,queueName,routeKey);
    }

    public Queue queue(){
        return this.queue;
    }

    /**
     * 注册消费端
     */
    public void registerConsumer(){
        this.messageConsumer = MessageConsumer.instance(this.queue(),false);
        this.messageConsumer.receiveOnly(this.listensToEvents(),
                new MessageListener(MessageListener.Type.TEXT){

                    @Override
                    public void handleMessage(String type, String messageId, Date timestamp,
                                              String textMessage, long deliveryTag, boolean isRedelivery) {
                        //pro process
                        filteredDispatch(type,textMessage);
                        //after process
                    }
                });
    }

}
