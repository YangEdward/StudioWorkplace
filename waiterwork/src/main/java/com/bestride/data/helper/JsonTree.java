package com.bestride.data.helper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonTree {

    private JsonTree(){
    }

	public static <T> JsonElement toJsonTree(List<T> target){
		Gson gson = new Gson();
		Type listType = new TypeToken<List<T>>() {}.getType();
		return gson.toJsonTree(target, listType);
	}
	public static <T> T fromJson(JsonElement json,Class<T> mT){
		Gson gson = new Gson();
		return gson.fromJson(json, mT);	
	}
	public static JsonObject toJson(Object obj){
		Gson gson = new Gson();
		return (JsonObject) gson.toJsonTree(obj);
	}
}
