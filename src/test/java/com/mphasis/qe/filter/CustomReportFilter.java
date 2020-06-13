package com.mphasis.qe.filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomReportFilter implements Filter {
    private StringBuilder requestData;
    private StringBuilder responseData;
    private int statusCode;

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
        requestData = new StringBuilder();
        requestData.append("\n");
        requestData.append("Request method: " + checkNull(filterableRequestSpecification.getMethod()));
        requestData.append("\n");
        requestData.append("Request URI: " + checkNull(filterableRequestSpecification.getURI()));
        requestData.append("\n");
        requestData.append("Form Params: " + checkNull(filterableRequestSpecification.getFormParams()));
        requestData.append("\n");
        requestData.append("Request Param: " + checkNull(filterableRequestSpecification.getRequestParams()));
        requestData.append("\n");
        requestData.append("Headers: " + checkNull(filterableRequestSpecification.getHeaders()));
        requestData.append("\n");
        requestData.append("Cookies: " + checkNull(filterableRequestSpecification.getCookies()));
        requestData.append("\n");
        requestData.append("Proxy: " + checkNull(filterableRequestSpecification.getProxySpecification()));
        requestData.append("\n");
        requestData.append("Body: " + checkNull(filterableRequestSpecification.getBody()));
        requestData.append("\n");
        requestData.append("******************************");
        responseData = new StringBuilder();
        responseData.append("\n"+"\n"+"\n");
        responseData.append("Status Code: "+response.getStatusCode());
        responseData.append("\n");
        responseData.append("Status Line: "+response.getStatusLine());
        responseData.append("\n");
        responseData.append("Response Cookies: "+response.getDetailedCookies());
        responseData.append("\n");
        responseData.append("Response Content Type: "+response.getContentType());
        responseData.append("\n");
        responseData.append("Response Headers: "+response.getHeaders());
        responseData.append("\n");
        responseData.append("Response Body: "+"\n"+response.getBody().prettyPrint());
        statusCode=response.getStatusCode();
        return response;
    }

    public String getRequestData() {
        return requestData.toString();
    }

    public String getResponseData() {
        return responseData.toString();
    }
    
    public int getStatusCode() {
    	return statusCode;
    }

    public String checkNull(Object o) {
        if (o == null)
            return null;
        else
            return o.toString();
    }

}