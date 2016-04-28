package com.saasovation.common.media;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.util.StringUtils;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 3:16
 * @since : ${VERSION}
 */
public class NotificationReader {
    private String jsonStr;
    private JsonObject jsonObject;
    public NotificationReader(String jsonStr) {
        assert !StringUtils.isEmpty(jsonStr);
        this.jsonStr = jsonStr;
        this.jsonObject = toJsonObject(this.jsonStr);
    }

    private JsonObject toJsonObject(String json){
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject();
    }

    public String stringValue(String name){
        return jsonObject.get(name).getAsString();
    }

    public JsonObject getAsJsonObject(String name){
        return jsonObject.getAsJsonObject(name);
    }


    public int intValue(String name){
        return jsonObject.get(name).getAsInt();
    }

    public long longValue(String name){
        return jsonObject.get(name).getAsLong();
    }

    public float floatValue(String name){
        return jsonObject.get(name).getAsFloat();
    }

    public double doubleValue(String name){
        return jsonObject.get(name).getAsDouble();
    }
}
