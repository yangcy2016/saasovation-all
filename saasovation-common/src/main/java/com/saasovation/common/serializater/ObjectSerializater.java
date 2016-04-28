package com.saasovation.common.serializater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectSerializater {
	private static final ObjectSerializater instance = new ObjectSerializater();
	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	public static ObjectSerializater newInstance(){
		return instance;
	}
	
	public String serialize(Object aDomainEvent){
		return gson.toJson(aDomainEvent);
	}
	
	public <T> T deserialize(String jsonString,Class<T> classOfT){
		return gson.fromJson(jsonString, classOfT);
	}
}
