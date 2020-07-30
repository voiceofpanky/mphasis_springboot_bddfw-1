package com.mphasis.qe.stepdefs;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.mphasis.qe.PropertySourceResolver;
import com.mphasis.qe.utils.ApiUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleGETSteps{
    private Response response;

    @Autowired
    PropertySourceResolver propertySourceResolver;
    
    @Autowired
    ApiUtil apiUtil;
    
	Map<String, String> queryParam = new HashMap<String, String>();

    @Given("I send a request to get the list of users")
    public void iSendARequestToGetTheListOfUsers() {
        response = apiUtil.getReq(propertySourceResolver.getAppApiUrl());
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
        response = apiUtil.getAsyncReq(url);
    }

    @Given("I have a userid {string}")
    public void i_have_a_userid(String id) {
    	queryParam.put("id", id);
    }

    @When("I send a request to fetch user details")
    public void i_send_a_request_to_fetch_user_details() {
    	 response = apiUtil.get(propertySourceResolver.getGetUserUrl(), queryParam);
    }
    
    @Then("I should not be able to fetch user details - force fail")
    public void i_should_not_be_able_to_fetch_user_details_force_fail() {
    	Assert.assertEquals(response.getStatusCode(), 200); 
    }
}
