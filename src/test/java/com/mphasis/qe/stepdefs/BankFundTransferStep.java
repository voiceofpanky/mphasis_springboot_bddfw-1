package com.mphasis.qe.stepdefs;

import java.util.Calendar;

import com.mphasis.qe.utils.Setup;
import org.testng.Assert;

import com.mphasis.qe.pages.BankLandingPage;
import com.mphasis.qe.pages.BankLoginPage;
import com.mphasis.qe.pages.BankRegistrationPage;
import com.mphasis.qe.pages.BasePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/
public class BankFundTransferStep extends BasePage{
	private BankLoginPage bankLoginPage;
	private BankLandingPage bankLandingPage;
	private BankRegistrationPage bankRegistrationPage;

	 public BankFundTransferStep() {
	        this.bankLoginPage = new BankLoginPage();
	        this.bankLandingPage = new BankLandingPage();
	        this.bankRegistrationPage=new BankRegistrationPage();
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
	
	@When("I navigate to bank online registration portal")
	public void iNavigateToBankOnlineRegistrationPortal() {
		bankRegistrationPage.linkRegister.click();
	}
	
	@When("I enter required personal information")
	public void iEnterRequiredPersonalInformation() {
		bankRegistrationPage.textfirstName.sendKeys("Jhon");
		bankRegistrationPage.textlastName.sendKeys("Kennedy");
		bankRegistrationPage.textAddress.sendKeys("3440 Walnut Avenue");
		bankRegistrationPage.textCity.sendKeys("Fremont");
		bankRegistrationPage.textState.sendKeys("CA");
		bankRegistrationPage.textZipCode.sendKeys("94538");
		bankRegistrationPage.textPhone.sendKeys("4085679856");
		bankRegistrationPage.textSsn.sendKeys("859657845");
		bankRegistrationPage.textUsername.sendKeys(Setup.username+Calendar.getInstance().getTimeInMillis());
		//System.out.println("Username::"+ Setup.username+Calendar.getInstance().getTimeInMillis());
		bankRegistrationPage.textPassword.sendKeys(Setup.password);
		bankRegistrationPage.textrepeatPassword.sendKeys(Setup.password);
		wait.forLoading(10);
	}
	
	@When("I submit registration form")
	public void iSubmitRegistrationForm() {
		bankRegistrationPage.btnRegister.click();
		wait.forElementToBeDisplayed(10, bankLandingPage.welcomeGreeting, "Welcome");
	}
	
	@When("I see welcome page")
	public void iSeeWelcomePage() {
		Assert.assertEquals(true, bankLandingPage.welcomeGreeting.isDisplayed());
	}
	
}
