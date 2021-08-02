package com.mphasis.qe.stepdefs;

import org.testng.Assert;

import com.mphasis.qe.pageobjects.BankFundTransfer;
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
    private BankFundTransfer bankFundTransfer;

    public BankFundTransferStep() {
        this.homePage = new HomePage();
        this.bankFundTransfer = new BankFundTransfer();
    }
	
    @Given("I am on bank homepage")
    public void iamAmOnBankHomepage() {
    	bankFundTransfer.gotoBankHomepage();
    }
    
    @Given("I login to my bank account successfully")
    public void iLoginToMyBankAccounSuccessfully() {
    	bankFundTransfer.login();
    }
    
    @Then("I am on account overview page")
    public void iAmonAccountOverviewPage(){
    	Assert.assertEquals(true, bankFundTransfer.verifyAcountOverview());
    }
    
    @Given("I am logged in to my bank account overview page")
    public void iAmLoggedInToMyAccountOverviewPage()  {
    	iamAmOnBankHomepage();
    	iLoginToMyBankAccounSuccessfully();
    }
    
    @Given("I click on {string}")
    public void iClickonMenu(String string){
    	bankFundTransfer.navigateToTransferFund();
    }
    
    @Given("I enter {string}")
    public void iEnterAmount(String amount) {
    	bankFundTransfer.enterAmount(amount);
    }
    
    @Given("select From {string} and To {string}")
    public void selectFromAccountAndToAccount(String fromAccount, String toAccount) {
    	bankFundTransfer.selectAccounts(fromAccount,toAccount);
    }
    
    @When("click on Transfer")
    public void clickOnTransfer() {
    	bankFundTransfer.hitTransfer();
    }
    
    @When("I should see Transfer Confirmation page")
    public void iShouldSeeTransferConfirmationPage() {
	Assert.assertEquals(true, bankFundTransfer.verifyTransferConfirmation());

    }
    
}
