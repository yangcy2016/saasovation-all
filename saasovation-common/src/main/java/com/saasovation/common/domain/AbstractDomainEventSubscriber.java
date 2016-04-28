package com.saasovation.common.domain;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author : huanghy
 * @create : 2016/4/19 0019 обнГ 3:21
 * @since : ${VERSION}
 */
public abstract class AbstractDomainEventSubscriber<T extends DomainEvent> implements DomainEventSubscriber<T> {
    public Class<T> subscribedToEventType() {
        Class<T> clazz = null;
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] types = ((ParameterizedType)t).getActualTypeArguments();
            clazz = (Class<T>)types[0];
        }
        return clazz;
    }
}
