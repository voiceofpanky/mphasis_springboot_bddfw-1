package com.mphasis.qe.utils;

import java.util.HashMap;

import org.springframework.stereotype.Component;
 

@Component
public class Constants {

	public static HashMap<Integer, String> httpCode = new HashMap<Integer, String>();
	public static HashMap<String, String> uiError = new HashMap<String, String>();

	Constants() {
		httpCode.put(400, "Bad Request");
		httpCode.put(401, "UnAuthorized");
		httpCode.put(403, "Forbidden");
		httpCode.put(404, "Data Not Found");
		httpCode.put(415, "Data Issue");
		httpCode.put(500, "Server Issue");
		httpCode.put(503, "Server Issue");
		httpCode.put(504, "Server Issue");
		uiError.put("org.openqa.selenium.ElementClickInterceptedException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.ElementNotInteractableException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.ElementNotSelectableException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.ElementNotVisibleException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.ImeActivationFailedException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.ImeNotAvailableException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.InvalidArgumentException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.InvalidCookieDomainException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.InvalidElementStateException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.InvalidSelectorException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.JavascriptException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.NoAlertPresentException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.NoSuchContextException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.NoSuchCookieException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.NoSuchElementException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.NoSuchFrameException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.NoSuchSessionException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.NoSuchWindowException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.NotFoundException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.ScriptTimeoutException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.SessionNotCreatedException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.StaleElementReferenceException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.TimeoutException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.UnableToSetCookieException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.UnhandledAlertException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.UnsupportedCommandException", "UI-SeleniumException");
		uiError.put("org.openqa.selenium.WebDriverException", "UI-SeleniumException");

	}

	public static String getHttpMessage(int errorCode) {
		return httpCode.get(errorCode);
	}

	public static String getUIErrorMessage(String errorCode) {
		
	 	if (uiError.containsKey(errorCode)==true) {
			
			return uiError.get(errorCode);
		}
		else
			
		return "OtherUIError";
	}

}