package com.mphasis.qe.stepdefs;

import com.mphasis.qe.PropertySourceResolver;
import com.mphasis.qe.pageobjects.FileHandler;
import com.mphasis.qe.utils.FileReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class FileHandlerSteps extends FileReader {

    private FileHandler fileHandler;

    @Autowired
    PropertySourceResolver propertySourceResolver;

    public FileHandlerSteps() {
        this.fileHandler = new FileHandler();
    }

    private String fileType = propertySourceResolver.getDataSource();

    List<String> loginInfo;


    @Given("User on the webAppSecurity  login page")
    public void userOnTheWebAppSecurityLoginPage() {
        this.fileHandler.goToWebAppSecurityPage();
        loginInfo = readFile(fileType);
    }

    @When("User entered <{string}> and <{string}>")
    public void userEnteredAnd(String userName, String password) {
        fileHandler.enterLoginCredentials(loginInfo.get(0), loginInfo.get(1));
    }

    @Then("User Navigated to account summary page")
    public void userNavigatedToAccountSummaryPage() {
        fileHandler.submit();
    }
}
