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
        response = postReq(propertySourceResolver.getAppApiUrl(), jsonBody);
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

    @Then("I should get a confirmation of the addition - force fail")
    public void iShouldGetAConfirmationOfTheAdditionForceFail() {
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
