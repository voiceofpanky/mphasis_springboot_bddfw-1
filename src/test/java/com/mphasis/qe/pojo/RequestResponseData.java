package com.mphasis.qe.pojo;


import io.restassured.http.Cookies;
import lombok.Data;

@Data
public class RequestResponseData {
	
	String requestMethod;
	String requestUri;
	String requestFormParams;
	String requestParams;
	String requestHeaders;
	String requestCookies;
	String requestProxySpecification;
	String requestBody;
	int responseStatusCode;
	String responseStatusLine;
	Cookies responseCookies;
	String responseContentType;
	String responseHeaders;
	String responseBody;

}
