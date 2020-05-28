package com.mphasis.qe.stepdefs;

import com.mphasis.qe.PropertySourceResolver;
import com.mphasis.qe.utils.ApiUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class SampleGETSteps extends ApiUtil {
    private Response response;

    @Autowired
    PropertySourceResolver propertySourceResolver;

    @Given("I send a request to get the list of users")
    public void iSendARequestToGetTheListOfUsers() {
        response = getReq(propertySourceResolver.getAppApiUrl());
    }

    @When("I have the list of users with me")
    public void iHaveTheListOfUsersWithMe() {
        Assert.assertEquals(response.getStatusCode(), 200);
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
