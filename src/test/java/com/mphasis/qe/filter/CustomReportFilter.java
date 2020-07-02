package com.mphasis.qe.filter;

import com.mphasis.qe.pojo.RequestResponseData;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomReportFilter implements Filter {
    private StringBuilder requestHooksData;
    private StringBuilder responseHooksData;
    private RequestResponseData requestResponseData;

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
        requestHooksData = new StringBuilder();
        requestHooksData.append("\n");
        requestHooksData.append("Request method: " + checkNull(filterableRequestSpecification.getMethod()));
        requestHooksData.append("\n");
        requestHooksData.append("Request URI: " + checkNull(filterableRequestSpecification.getURI()));
        requestHooksData.append("\n");
        requestHooksData.append("Form Params: " + checkNull(filterableRequestSpecification.getFormParams()));
        requestHooksData.append("\n");
        requestHooksData.append("Request Param: " + checkNull(filterableRequestSpecification.getRequestParams()));
        requestHooksData.append("\n");
        requestHooksData.append("Headers: " + checkNull(filterableRequestSpecification.getHeaders()));
        requestHooksData.append("\n");
        requestHooksData.append("Cookies: " + checkNull(filterableRequestSpecification.getCookies()));
        requestHooksData.append("\n");
        requestHooksData.append("Proxy: " + checkNull(filterableRequestSpecification.getProxySpecification()));
        requestHooksData.append("\n");
        requestHooksData.append("Body: " + checkNull(filterableRequestSpecification.getBody()));
        requestHooksData.append("\n");
        requestHooksData.append("******************************");
        responseHooksData = new StringBuilder();
        responseHooksData.append("\n"+"\n"+"\n");
        responseHooksData.append("Status Code: "+response.getStatusCode());
        responseHooksData.append("\n");
        responseHooksData.append("Status Line: "+response.getStatusLine());
        responseHooksData.append("\n");
        responseHooksData.append("Response Cookies: "+response.getDetailedCookies());
        responseHooksData.append("\n");
        responseHooksData.append("Response Content Type: "+response.getContentType());
        responseHooksData.append("\n");
        responseHooksData.append("Response Headers: "+response.getHeaders());
        responseHooksData.append("\n");
        responseHooksData.append("Response Body: "+"\n"+response.getBody().prettyPrint());

        requestResponseData = new RequestResponseData();
        requestResponseData.setRequestMethod(checkNull(filterableRequestSpecification.getMethod()));
        requestResponseData.setRequestUri(checkNull(filterableRequestSpecification.getURI()));
        requestResponseData.setRequestFormParams(checkNull(filterableRequestSpecification.getFormParams()));
        requestResponseData.setRequestHeaders(checkNull(filterableRequestSpecification.getHeaders()));
        requestResponseData.setRequestCookies(checkNull(filterableRequestSpecification.getCookies()));
        requestResponseData.setRequestProxySpecification(checkNull(filterableRequestSpecification.getProxySpecification()));
        requestResponseData.setRequestBody(checkNull(filterableRequestSpecification.getBody()));
        requestResponseData.setResponseStatusCode(response.getStatusCode());
        requestResponseData.setResponseStatusLine(response.getStatusLine());
        requestResponseData.setResponseCookies(response.getDetailedCookies());
        requestResponseData.setResponseContentType(response.getContentType());
        requestResponseData.setResponseBody(response.getBody().prettyPrint());
        
        return response;
    }

    public String getRequestHooksData() {
        return requestHooksData.toString();
    }

    public String getResponseHooksData() {
        return responseHooksData.toString();
    }
    
    public RequestResponseData getRequestResponseData() {
    	return requestResponseData;
    }

    public String checkNull(Object o) {
        if (o == null)
            return null;
        else
            return o.toString();
    }
}