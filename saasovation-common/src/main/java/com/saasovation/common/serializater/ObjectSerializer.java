package com.saasovation.common.serializater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectSerializer {

	private static final ObjectSerializer instance = new ObjectSerializer();
	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	public static ObjectSerializer newInstance(){
		return instance;
	}
	
	public String serialize(Object aDomainEvent){
		return gson.toJson(aDomainEvent);
	}
	
	public <T> T deserialize(String jsonString,Class<T> classOfT){
		return gson.fromJson(jsonString, classOfT);
	}
}
