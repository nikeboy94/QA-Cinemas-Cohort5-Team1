package com.qa.cinema.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;;

public class JSONUtil {

	private Gson gson;

	public JSONUtil() {
		this.gson = new Gson();
	}

	public String getJSONForObject(Object obj) {
		return gson.toJson(obj);
	}

	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		return gson.fromJson(jsonString, clazz);
	}
	
	public String getRedactedJSONForObject(Object obj) {
		GsonBuilder builder = new GsonBuilder();
		JsonObject json = (JsonObject)builder.create().toJsonTree(obj);
		json.remove("password");
		json.remove("salt");
		return gson.toJson(json);
	}

}
