package com.mphasis.qe.stepdefs;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mphasis.qe.pageobjects.mphasis.MphasisServiceNowPage;
import com.mphasis.qe.utils.Setup;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import  io.cucumber.spring.CucumberContextConfiguration;
/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MphasisServiceNowPageSteps {
    private MphasisServiceNowPage serviceNowPage;
    public MphasisServiceNowPageSteps() {
        this.serviceNowPage = new MphasisServiceNowPage();
    }

    @Given("I am on the {string} page")
    public void aUserNavigatesToHomePage(String pageName) {
        switch(pageName){
            case "Service Now":
                this.serviceNowPage.goToServiceNowPage();
                break;
        }
    }
    @And("I login successfully")
    public void iLoginSuccessfully(){
        this.serviceNowPage.setLoginText(Setup.username);
        this.serviceNowPage.clickNextBtn();
        this.serviceNowPage.setPasswordInput(Setup.password);
        this.serviceNowPage.clickSignIn();
        this.serviceNowPage.clickYesBtn();
    }
    @Then("the page title is {string}")
    public void thePageTitleIs(String title){
        this.serviceNowPage.driver.getTitle().equalsIgnoreCase("Self Service Portal - Mphasis SP CIO");
    }
    @Then("a warning message is displayed on page")
    public void aWarningMessageIsDisplaydOnPage(){

    }
}

