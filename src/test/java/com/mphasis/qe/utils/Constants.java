package com.mphasis.qe.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Constants {

	public static Map<Integer, String> httpCode = new HashMap<Integer, String>(); 
	public static Map<String, String> uiError = new HashMap<String, String>(); 

	Constants(){
		httpCode.put(400, "Bad Request");
		httpCode.put(401, "UnAuthorized");
		httpCode.put(403, "Forbidden");
		httpCode.put(404, "Not Found");
		
		uiError.put("NoSuchElementException", "There is no such element");
		uiError.put("ElementNotVisibleException", "Element Not Visible");

	}
	public static String getHttpMessage(int errorCode) {
		return httpCode.get(errorCode);
	}
	
	public static String getUIErrorMessage(int errorCode) {
		return uiError.get(errorCode);
	}

}
