package com.saasovation.common.container;

import org.springframework.context.ApplicationContext;

/**
 * @author : huanghy
 * @create : 2016/4/22 0022 下午 1:54
 * @since : ${VERSION}
 */
public class Container {
    private ApplicationContext context;
    private static Container instance = new Container();
    public static Container instance(){
        return instance;
    }

    public Container init(ApplicationContext context){
        this.context = context;
        return this;
    }

    public ApplicationContext getContext(){
        if(context == null){
            throw new IllegalStateException("container must be init at first!");
        }
        return context;
    }

    public <T> T getBean(String name,Class<T> clazz){
        return getContext().getBean(name,clazz);
    }

    public Object getBean(String name){
        return getContext().getBean(name);
    }
}
