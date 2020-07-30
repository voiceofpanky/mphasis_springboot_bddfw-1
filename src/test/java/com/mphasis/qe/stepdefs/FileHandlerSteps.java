package com.mphasis.qe.stepdefs;

import java.util.List;

import com.mphasis.qe.pageobjects.FileHandlerPage;
import com.mphasis.qe.utils.FileReader;
import com.mphasis.qe.utils.Setup;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileHandlerSteps extends FileReader {

    private String fileType = Setup.dataSource;
    private String filePath = Setup.dataPath;

    private FileHandlerPage fileHandlerPage;
    public FileHandlerSteps() {
        this.fileHandlerPage = new FileHandlerPage();
    }

    List<String> loginInfo;

    @Given("User on the webAppSecurity  login page")
    public void userOnTheWebAppSecurityLoginPage() {
        this.fileHandlerPage.goToWebAppSecurityPage();
        loginInfo = readFile(fileType, filePath);
    }

    @When("User entered <{string}> and <{string}>")
    public void userEnteredAnd(String userName, String password) {
        fileHandlerPage.enterLoginCredentials(loginInfo.get(0), loginInfo.get(1));
    }

    @Then("User Navigated to account summary page")
    public void userNavigatedToAccountSummaryPage() {
        fileHandlerPage.submit();
    }
}
