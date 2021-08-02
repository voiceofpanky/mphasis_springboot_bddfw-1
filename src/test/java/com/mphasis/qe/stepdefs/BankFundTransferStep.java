package com.mphasis.qe.stepdefs;

import com.mphasis.qe.pageobjects.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;


/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/
@Slf4j
public class BankFundTransferStep {

    private HomePage homePage;

    public BankFundTransferStep() {
        this.homePage = new HomePage();
    }
	
    @Given("I am on bank homepage")
    public void iamAmOnBankHomepage() {
    	
    }
    
    @Given("I login to my bank account successfully")
    public void iLoginToMyBankAccounSuccessfully() {
    	
    }
    
    @Then("I am on account overview page")
    public void iAmonAccountOverviewPage()
    {
    	
    }
    
    @Given("I am logged in to my bank account overview page")
    public void iAmLoggedInToMyAccountOverviewPage()
    {
    	
    }
    
    @Given("I click on {string}")
    public void iClickonMenu(String string)
    {
    	
    }
    
    @Given("I enter {string}")
    public void iEnterAmount(String amount) {
    	
    }
    
    @Given("select From {string} and To {string}")
    public void selectFromAccountAndToAccount(String fromAccount, String toAccount) {
    	
    }
    
    @When("click on Transfer")
    public void clickOnTransfer() {
    	
    }
    
    @When("I should see Transfer Confirmation page")
    public void iShouldSeeTransferConfirmationPage() {
    	
    }
    
}
