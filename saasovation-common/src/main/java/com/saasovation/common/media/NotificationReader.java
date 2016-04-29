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

    private JsonObject inner;

    private static final String SPLIT_DOT = "\\.";
    private static final String CON_DOT   = ".";
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
        check(name);
        if(name.contains(CON_DOT)){
            return cascadeStringCall(name);
        }
        return jsonObject.get(name).getAsString();
    }

    private void check(String name){
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("name must not be null");
        }
    }


    public JsonObject getAsJsonObject(String name){
        return jsonObject.getAsJsonObject(name);
    }


    public int intValue(String name){
        check(name);
        if(name.contains(CON_DOT)){
            return cascadeCallIntValue(name);
        }
        return jsonObject.get(name).getAsInt();
    }

    public boolean booleanValue(String name){
        check(name);
        if(name.contains(CON_DOT)){
            return cascadeCallBooleanValue(name);
        }
        return jsonObject.get(name).getAsBoolean();
    }

    public long longValue(String name){
        check(name);
        if(name.contains(CON_DOT)){
            return cascadeCallLongValue(name);
        }
        return jsonObject.get(name).getAsLong();
    }

    public float floatValue(String name){
        check(name);
        if(name.contains(CON_DOT)){
            return cascadeCallFloatValue(name);
        }
        return jsonObject.get(name).getAsFloat();
    }

    public double doubleValue(String name){
        check(name);
        if(name.contains(CON_DOT)){
            return cascadeCallDoubleValue(name);
        }
        return jsonObject.get(name).getAsDouble();
    }

    private String cascadeStringCall(String name){
        String attr = parse(name);
        return inner.get(attr).getAsString();
    }

    private int cascadeCallIntValue(String name){
       String attr = parse(name);
       return inner.get(attr).getAsInt();
    }

    private float cascadeCallFloatValue(String name){
        String attr = parse(name);
        return inner.get(attr).getAsFloat();
    }
    private double cascadeCallDoubleValue(String name){
        String attr = parse(name);
        return inner.get(attr).getAsDouble();
    }
    private boolean cascadeCallBooleanValue(String name){
        String attr = parse(name);
        return inner.get(attr).getAsBoolean();
    }
    public long cascadeCallLongValue(String name){
        String attr = parse(name);
        return inner.get(attr).getAsLong();
    }

    private String parse(String name){
        String[] contents = name.split(SPLIT_DOT);
        String attr = contents[contents.length-1];
        JsonObject jsonObj = getAsJsonObject(contents[0]);
        this.inner = jsonObj;
        for(int i = 1;i<contents.length-1;i++){
            jsonObj = getInner(contents[i]);
            this.inner = jsonObj;
        }
        return attr;
    }

    private JsonObject getInner(String name){
        return inner.getAsJsonObject(name);
    }


    public static void main(String[] args) {
        String str = "{huang:{id:{acd:2345}}}";
        String ss= new NotificationReader(str).stringValue("huang.id.acd");
        System.out.println(ss);
    }
}
