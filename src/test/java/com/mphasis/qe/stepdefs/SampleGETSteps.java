package com.mphasis.qe.stepdefs;

import com.mphasis.qe.utils.ApiUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class SampleGETSteps extends ApiUtil {
    private Response response;

    @Given("I send a GET request to {string}")
    public void send_getRequest(String url){
        response = getReq(url);
    }

    @Then("I should get a status code {string}")
    public void verify_StatusCode(String statusCode){
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
    }

    @Then("I should see the list has the user firstname as {string}")
    public void verify_ifUserPresent(String userName){
        assertThat(response.getBody().jsonPath().get("data.first_name"), hasItem(userName));
    }

    @Then("I should see response conforms to json schema {string}")
    public void iShouldSeeResponseConformsToJsonSchema(String schemaPath) {
        assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath(schemaPath));
    }

    @Given("I send an Async GET request to {string}")
    public void iSendAnAsyncGETRequestTo(String url) {
        response = getAsyncReq(url);
    }
}
