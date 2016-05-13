package com.saasovation.common.port.adapter.messaging.rabbitmq;

/**
 * @author : huanghy
 * @create : 2016/5/13 0013 上午 10:52
 * @since : ${VERSION}
 */
public class TextDecoder implements Decoder<String>{

    private static final String DEFAULT_CHARSET = "UTF-8";

    @Override
    public String decode(byte[] body) {
        return decode(body,DEFAULT_CHARSET);
    }

    public String decode(byte[] body,String charset){
        String result = null;
        try {
            result =  new String(body,charset);
        }catch (Exception e){
            throw new MessageDecoderException(e);
        }
        return result;
    }
}
