package com.util;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorUtils {
	public static String customErrors(List<ObjectError> errors) {
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject messages = new JSONObject();
			jsonObject.put("status", "failure");
			jsonObject.put("title", "Field Errors");
			for (ObjectError objectError : errors) {
				if(objectError instanceof FieldError) {
					FieldError fieldError = (FieldError) objectError;
					messages.put(fieldError.getField(), fieldError.getDefaultMessage());
				}
			}
			jsonObject.put("messages", messages);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
}
