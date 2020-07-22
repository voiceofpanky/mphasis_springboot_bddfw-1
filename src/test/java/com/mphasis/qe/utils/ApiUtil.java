package com.mphasis.qe.utils;

import static org.awaitility.Awaitility.with;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.mphasis.qe.exception.StatusCodeMismatchException;
import com.mphasis.qe.filter.CustomReportFilter;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
/**
*  @author: Dipanjan Chakraborty
 *  Util c  lass to handle all API calls
* */

@Slf4j
@Component
public class ApiUtil {
	private static RequestSpecification request;
	private static Response response;
	Filter reportFilter = new CustomReportFilter();
	private static final SecureRandom random = new SecureRandom();

	public ApiUtil() {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		builder.addFilter(reportFilter);
		request = RestAssured.given().spec(builder.build());
	}

	public Response getReq(String url) {
		try {
			response = request.get(new URI(url));
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response get(String url, Map<String, String> queryParam) {
		try {
			request.queryParams(queryParam);
			response = request.get(new URI(url));
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response getReq(String url, Map<String, String> headers) {
		request.headers(headers);
		try {
			Response response = request.get(new URI(url));
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response postReq(String url, String jsonBody) {
		request.body(jsonBody);
		try {
			Response response = request.post(new URI(url));
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response postReq(String url, Map<String, String> headers, String jsonBody) {
		request.headers(headers);
		request.body(jsonBody);

		try {
			response = request.post(new URI(url));
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response putReq(String url, String jsonBody) {
		request.body(jsonBody);
		try {
			Response response = request.put(new URI(url));
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response putReq(String url, Map<String, String> headers, String jsonBody) {
		request.headers(headers);
		request.body(jsonBody);
		try {
			Response response = request.put(new URI(url));
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response deleteReq(String url) {
		try {
			Response response = request.delete(new URI(url));
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response deleteReq(String url, Map<String, String> headers) {
		request.headers(headers);
		try {
			Response response = request.delete(new URI(url));
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response getAsyncReq(String url) {
		try {
			response = request.get(new URI(url));

			with().pollInterval(1000, TimeUnit.MILLISECONDS).and().with().pollDelay(20, TimeUnit.MILLISECONDS)
					.await("receiving response").atMost(180, TimeUnit.SECONDS).until(() -> response != null);
			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response getAsyncReq(String url, Map<String, String> headers) {
		request.headers(headers);
		try {
			Response response = request.get(new URI(url));
			with().pollInterval(1000, TimeUnit.MILLISECONDS).and().with().pollDelay(20, TimeUnit.MILLISECONDS)
					.await("receiving response").atMost(180, TimeUnit.SECONDS).until(() -> response != null);

			return response;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Response postReq(String appRegisterUrl, JSONObject requestBody) {
		request.body(requestBody.toString());
		response = request.post(appRegisterUrl);
		return response;
	}

	public Filter getReportFilter() {
		return reportFilter;
	}

	/**
	 * @author: Vikas Swami Added new custom methods
	 */

	public static String getResponseBody() {
		return response.asString();
	}

	public static long getResponseTime() {
		return response.getTime();
	}

	public static boolean checkIfResponseBodyIsEmpty() {
		return response.asString().equals("[]");
	}

	public static void setCredentialsWithHeader(String username, String password) {
		request.auth().preemptive().basic(username, password).when();
	}

	public static boolean matchStatusCode(int correctStatusCode) throws StatusCodeMismatchException {
		int currentStatusCode = response.getStatusCode();
		if (currentStatusCode == correctStatusCode) {
			return true;
		} else
			throw new StatusCodeMismatchException("Status Code is " + currentStatusCode + " Not " + correctStatusCode);
	}

	public static String generateRandomNumericString(int length) {
		String textnumber = "0123456789";
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(textnumber.charAt(random.nextInt(textnumber.length())));
		return sb.toString();

	}

	public static String generateRandomString(int length) {

		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(text.charAt(random.nextInt(text.length())));
		return sb.toString();

	}

}