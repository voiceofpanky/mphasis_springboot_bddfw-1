package com.mphasis.qe.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.mphasis.qe.filter.CustomReportFilter;
import com.mphasis.qe.pojo.ReportData;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static org.awaitility.Awaitility.*;

@Slf4j
@Component
public class ApiUtil{

    RequestSpecification request;
    Response response;
    Filter reportFilter=new CustomReportFilter();

    public ApiUtil(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.addFilter(reportFilter);
        request = RestAssured.given().spec(builder.build());
    }

    public Response getReq(String url){
        try {
            response = request.get(new URI(url));
            return  response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Response get(String url, Map<String, String> queryParam){
        try {
        	request.queryParams(queryParam);
            response = request.get(new URI(url));
            return  response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response postReq(String url, String jsonBody){

        request.body(jsonBody);        
        try {
            response = request.post(new URI(url));
            return  response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response getAsyncReq(String url){
        try {
              response =  request.get(new URI(url));

            with().pollInterval(1000, TimeUnit.MILLISECONDS)
                    .and().with().pollDelay(20, TimeUnit.MILLISECONDS).await("receiving response")
                    .atMost(180, TimeUnit.SECONDS)
                    .until(() -> response != null);
            return  response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Filter getReportFilter() {
    	return reportFilter;
    }
    
    public ReportData getReportData() {
        ReportData reportData = new ReportData();
        reportData.setStatusCode(response.getStatusCode());
    	return reportData;
    }

	public Response postReq(String appRegisterUrl, JSONObject requestBody) {
		request.body(requestBody.toString());
		response = request.post(appRegisterUrl);
		return response;
	}
}
