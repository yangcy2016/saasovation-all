package com.saasovation.common.media;

import org.springframework.util.StringUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RepresentationReader {
	private String jsonStr;
	private JsonObject jsonObject;
	public RepresentationReader(String jsonStr) {
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
