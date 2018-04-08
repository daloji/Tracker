package com.daloji.tracker.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static  <T> Object jsonToObject(String json, Class<T> clazz) {

		ObjectMapper objectMapper = new ObjectMapper();
		T  obj =null;
		try {
			obj = (T) objectMapper.readValue(json,clazz);

		} catch (IOException e) {
e.printStackTrace();
		}
		return obj;		
	}

	public static  String objectToJson(Object object) {

		ObjectMapper objectMapper = new ObjectMapper();
		String json=null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		return json;
	}
}
