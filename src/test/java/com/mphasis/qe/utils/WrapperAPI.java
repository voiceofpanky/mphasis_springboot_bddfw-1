import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.mphasis.qe.stepdefs.RequestData;

@Slf4j
@Getter
@Component
@EnableConfigurationProperties(PropertiesAPI.class)
public class WrapperAPI {

	private PropertiesAPI propertiesAPI;
	private List<RequestData> requestInfo = new ArrayList<>();

	public void setRequestInfo(List<RequestData> requestInfo) {
		this.requestInfo = requestInfo;
	}

	@Autowired
	public WrapperAPI(PropertiesAPI propertiesAPI) {
		this.propertiesAPI = propertiesAPI;
	}

	public ValidatableResponse post(String host, String url, String body) {
		RequestSpecification requestSpecification = requestSpecification(host, url, body, null, null);
		ValidatableResponse response = given().spec(requestSpecification).post().then();
		saveRequestAndResponse("POST", requestSpecification, response);
		return response;
	}

	public ValidatableResponse post(String host, String url, String body, Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(host, url, body, null, headers);
		ValidatableResponse response = given().spec(requestSpecification).post().then();
		saveRequestAndResponse("POST", requestSpecification, response);
		return response;
	}

	public ValidatableResponse post(String host, String url, String body, Map<String, String> params,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(host, url, body, params, headers);
		ValidatableResponse response = given().spec(requestSpecification).post().then();
		saveRequestAndResponse("POST", requestSpecification, response);
		return response;
	}

	public ValidatableResponse patch(String host, String url, String body) {
		RequestSpecification requestSpecification = requestSpecification(host, url, body, null, null);
		ValidatableResponse response = given().spec(requestSpecification).patch().then();
		saveRequestAndResponse("PATCH", requestSpecification, response);
		return response;
	}

	public ValidatableResponse patch(String host, String url, String body, Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(host, url, body, null, headers);
		ValidatableResponse response = given().spec(requestSpecification).patch().then();
		saveRequestAndResponse("PATCH", requestSpecification, response);
		return response;
	}

	public ValidatableResponse put(String host, String url) {
		RequestSpecification requestSpecification = requestSpecification(host, url, null, null, null);
		ValidatableResponse response = given().spec(requestSpecification).put().then();
		saveRequestAndResponse("PUT", requestSpecification, response);
		return response;
	}

	public ValidatableResponse get(String host, String url, Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(host, url, null, null, headers);
		ValidatableResponse response = given().spec(requestSpecification).get().then();
		saveRequestAndResponse("GET", requestSpecification, response);
		return response;
	}

	public ValidatableResponse get(String host, String url, Map<String, String> params, Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(host, url, null, params, headers);
		ValidatableResponse response = given().spec(requestSpecification).get().then();
		saveRequestAndResponse("GET", requestSpecification, response);
		return response;
	}

	public ValidatableResponse delete(String host, String url, Map<String, String> params,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(host, url, null, params, headers);
		ValidatableResponse response = given().spec(requestSpecification).delete().then();
		saveRequestAndResponse("DELETE", requestSpecification, response);
		return response;
	}

	private RequestSpecification requestSpecification(String host, String url, String body, Map<String, String> params,
			Map<String, String> headers) {
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

		requestSpecBuilder.setBaseUri(host).setBasePath(url)
				.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
				.setUrlEncodingEnabled(false).setContentType(ContentType.JSON);

		if (params != null) {
			requestSpecBuilder.addQueryParams(params);
		}

		if (body != null) {
			requestSpecBuilder.setBody(body);
		}

		headers = addApigeeKeyHeader(headers);
		requestSpecBuilder.addHeaders(headers);

		return requestSpecBuilder.build();
	}

	private Map<String, String> addApigeeKeyHeader(Map<String, String> headers) {
		if (headers == null) {
			headers = new HashMap<>();
		}
		headers.put("apikey", getPropertiesAPI().getApigeeKey());
		if (propertiesAPI.isTrainingheader())
			headers = addApigeeTrainingHeader(headers);

		return headers;
	}

	public Integer getResponseStatusCode(ValidatableResponse response) {
		return response.extract().statusCode();
	}

	public String getResponseBodyAsString(ValidatableResponse response) {
		return response.extract().body().asString();
	}

	public JSONArray getResponseBodyAsJSONArray(ValidatableResponse response) {
		try {
			return new JSONArray(getResponseBodyAsString(response));
		} catch (JSONException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public JSONObject getResponseBodyAsJSONObject(ValidatableResponse response) {
		try {
			return new JSONObject(getResponseBodyAsString(response));
		} catch (JSONException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	private Map<String, String> addApigeeTrainingHeader(Map headers) {
		headers.put("environment", "training");
		return headers;
	}

	public void saveRequestAndResponse(String method, RequestSpecification requestSpecification,
			ValidatableResponse response) {
		try {
			if (propertiesAPI.isRequireReport()) {
				RequestSpecificationImpl requestDetails = (RequestSpecificationImpl) requestSpecification;

				int responseCode = response.extract().statusCode();
				String reqResponse = response.extract().body().asString();

				RequestData requestData = new RequestData();

				requestData.setRequestType(method);
				requestData.setUrl(requestDetails.getBaseUri() + requestDetails.getBasePath());
				requestData.setParameters(requestDetails.getQueryParams().toString());
				requestData.setBody(requestDetails.getBody().toString());
				requestData.setResponse(reqResponse);
				requestData.setStatusCode(responseCode);
				requestInfo.add(requestData);
			}
		} catch (Exception e) {
		}
	}
}