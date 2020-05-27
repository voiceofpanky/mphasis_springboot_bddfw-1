package com.mphasis.qe.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.awaitility.Awaitility.*;

public class ApiUtil{

    public RequestSpecification request;

    public static Map<String, Response> requestResponseMap;

    public ApiUtil(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        request = RestAssured.given().spec(builder.build());
        requestResponseMap = new HashMap<>();
    }

    public Response getReq(String url){
        try {
            Response response = request.get(new URI(url));
            requestResponseMap.put("", response);
            return  response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response postReq(String url, String jsonBody){

        request.body(jsonBody);
        try {
            Response response = request.post(new URI(url));
            requestResponseMap.put(jsonBody, response);
            return  response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response getAsyncReq(String url){
        try {
            Response response =  request.get(new URI(url));

            with().pollInterval(1000, TimeUnit.MILLISECONDS)
                    .and().with().pollDelay(20, TimeUnit.MILLISECONDS).await("receiving response")
                    .atMost(180, TimeUnit.SECONDS)
                    .until(() -> response != null);

            requestResponseMap.put("", response);
            return  response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
