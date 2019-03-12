package com.shop.api.test.utils;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The Class TestUtils.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2019-03-13
 */
public class TestUtils {

	/**
	 * Json to list.
	 *
	 * @param json the json
	 * @param token the token
	 * @return the list
	 */
	@SuppressWarnings("rawtypes")
	public static List jsonToList(String json, TypeToken token) {
		Gson gson = new Gson();
		return gson.fromJson(json, token.getType());
	}

	/**
	 * Object to json.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public static String objectToJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/**
	 * Json to object.
	 *
	 * @param <T> the generic type
	 * @param json the json
	 * @param classOf the class of
	 * @return the t
	 */
	public static <T> T jsonToObject(String json, Class<T> classOf) {
		Gson gson = new Gson();
		return gson.fromJson(json, classOf);
	}
}
