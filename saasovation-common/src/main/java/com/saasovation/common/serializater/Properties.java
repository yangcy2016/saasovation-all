package com.saasovation.common.serializater;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 上午 9:36
 * @since : ${VERSION}
 */
public class Properties {

    public Properties() {
        content = new HashMap<>();
    }

    private Map<Object,Object> content ;

    public void add(Object key,Object value){
        content.put(key,value);
    }

    public boolean contains(Object key){
        return content.containsKey(key);
    }

    public Object get(Object key){
        return content.get(key);
    }

    public Object content(){
        return this.content;
    }
}
