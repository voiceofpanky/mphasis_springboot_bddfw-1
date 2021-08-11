package com.mphasis.qe.stepdefs;

import org.testng.Assert;

import com.mphasis.qe.pages.BankLandingPage;
import com.mphasis.qe.pages.BankLoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/
public class BankFundTransferStep {
	private BankLoginPage bankLoginPage;
	private BankLandingPage bankLandingPage;

	 public BankFundTransferStep() {
	        this.bankLoginPage = new BankLoginPage();
	        this.bankLandingPage = new BankLandingPage();
	    }
	@Given("I am on bank homepage")
	public void iamAmOnBankHomepage() {
		bankLoginPage.gotoBankHomepage();
	}

	@Given("I login to my bank account successfully")
	public void iLoginToMyBankAccounSuccessfully() {
		bankLoginPage.login();
	}

	@Then("I am on account overview page")
	public void iAmonAccountOverviewPage(){
		Assert.assertEquals(true, bankLandingPage.verifyAcountOverview());
	}

	@Given("I am logged in to my bank account overview page")
	public void iAmLoggedInToMyAccountOverviewPage()  {
		iamAmOnBankHomepage();
		iLoginToMyBankAccounSuccessfully();
	}

	@Given("I click on {string}")
	public void iClickonMenu(String string){
		bankLandingPage.transferFunds.click();
	}

	@Given("I enter {string}")
	public void iEnterAmount(String amount) {
		bankLandingPage.enterAmount(amount);
	}

	@Given("select From {string} and To {string}")
	public void selectFromAccountAndToAccount(String fromAccount, String toAccount) {
		bankLandingPage.selectAccounts(fromAccount,toAccount);
	}

	@When("click on Transfer")
	public void clickOnTransfer() {
		bankLandingPage.transfer.click();
	}

	@When("I should see Transfer Confirmation page")
	public void iShouldSeeTransferConfirmationPage() {
		Assert.assertEquals(true, bankLandingPage.verifyTransferConfirmation());
	}
}
