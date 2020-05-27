package com.mphasis.qe.stepdefs;

import com.mphasis.qe.pojo.User;
import com.mphasis.qe.utils.ApiUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class SamplePOSTSteps extends ApiUtil {
    private Response response;
    private String jsonBody;
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

    @When("I send a POST request to {string}")
    public void send_postRequest(String url)
    {
        response = postReq(url, jsonBody);
    }

    @Then("I should get the post status code as {string}")
    public void verify_StatusCode(String statusCode){
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
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
}
