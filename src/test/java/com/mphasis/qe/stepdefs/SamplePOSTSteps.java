package com.mphasis.qe.stepdefs;

import com.mphasis.qe.PropertySourceResolver;
import com.mphasis.qe.pojo.User;
import com.mphasis.qe.utils.ApiUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SamplePOSTSteps extends ApiUtil {
    private Response response;
    private String jsonBody;

    @Autowired
    PropertySourceResolver propertySourceResolver;
    
    @Autowired
    ApiUtil apiUtil; 
    
    Map<String, String> queryParam = new HashMap<String, String>();
    
    JSONObject requestBody = new JSONObject();

    @Given("I have a payload with a user details")
    public void generate_simple_payload(){
        Map<String, String> postPayload = new HashMap<String, String>();
        postPayload.put("name", "Eric");
        postPayload.put("job", "Auto Lead");

        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonBody = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(postPayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @When("I send a request to add the user to the list")
    public void iSendARequestToAddTheUserToTheList() {
        response = apiUtil.postReq(propertySourceResolver.getAppApiUrl(), jsonBody);
    }

    @Then("I should get a confirmation of the addition")
    public void iShouldGetAConfirmationOfTheAddition() {
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Given("I have a complex payload with a user details")
    public void iHaveAComplexPayloadWithAUserDetails() {
        User userObj = new User("Magicman", "Magician");
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonBody = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(userObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Given("I have a user with email id {string}")
    public void i_have_a_user_with_email_id(String email) throws JSONException {
    	requestBody.put("email", email); 
    }

    @When("I send a request to register the user")
    public void i_send_a_request_to_register_the_user() {
        response = apiUtil.postReq(propertySourceResolver.getAppRegisterUrl(), requestBody);
    }

    @Then("I should not be able to register - force fail")
    public void i_should_not_be_able_to_register_force_fail() {
    	Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("I should get a confirmation of the addition - force fail")
    public void iShouldGetAConfirmationOfTheAdditionForceFail() {
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
