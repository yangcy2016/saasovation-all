package com.saasovation.common.port.adapter.messaging.rabbitmq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : huanghy
 * @create : 2016/5/11 0011 上午 9:51
 * @since : ${VERSION}
 */
public class ExchangeListenerManager {
    private ConcurrentHashMap<String,ExchangeListener> listenerMap ;
    private static final ExchangeListenerManager instance = new ExchangeListenerManager();
    private ExchangeListenerManager(){
        listenerMap = new ConcurrentHashMap<>();
    }

    public static ExchangeListenerManager getInstance(){
        return instance;
    }

    public void add(String name,ExchangeListener listener){
        this.listenerMap.put(name,listener);
    }
    public ExchangeListener remove(String name){
        return listenerMap.remove(name);
    }

    public Map<String,ExchangeListener> listenerMap(){
        return listenerMap;
    }
}
