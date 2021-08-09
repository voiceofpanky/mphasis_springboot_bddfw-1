package com.mphasis.qe.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/
public class BankLoginPage extends BasePage {

    private final String PAGE_URL = "https://parabank.parasoft.com/parabank/index.htm";
    private BankLandingPage bankLandingPage;

    @FindBy(css = "input[name=username]")
    private WebElement username;

    @FindBy(css = "input[name=password]")
    private WebElement password;

    @FindBy(css = "input[type='submit']")
    private WebElement login;
    
    @FindBy(xpath ="//a[contains(text(),'Transfer Funds')]")
    private WebElement transferFunds;
    
    @FindBy(css ="input[name='input']")
    private WebElement amount;
  
    @FindBy(css ="#fromAccountId")
    private WebElement fromAccount;
    
    @FindBy(css ="#fromAccountId")
    private WebElement toAccount;
    
    @FindBy(css = "input[type='submit']")
    private WebElement Transfer;
    
    @FindBy(xpath ="//h1[contains(text(),'Transfer Complete')]")
    private WebElement TransferComplete;
    
    @FindBy(xpath ="//h1[contains(text(),'Accounts Overview')]")
    private WebElement AccountOverview;
    
    public BankLoginPage() {
        PageFactory.initElements(driver, this);
        this.bankLandingPage= new BankLandingPage();
    }
    public void gotoBankHomepage() {
    	 driver.get(PAGE_URL);
    }
    
    public BankLandingPage login(){
    	 this.username.sendKeys("admin");
    	 this.password.sendKeys("admin");
    	 this.login.click();
    	 return bankLandingPage;
    }
	public boolean verifyAcountOverview() {
		boolean isDisplayed=false;
		isDisplayed=AccountOverview.isDisplayed();
		return isDisplayed;
	}
    
	public void navigateToTransferFund() {
		transferFunds.click();
	}
	
	public void enterAmount(String amountToTransfer) {
		amount.sendKeys(amountToTransfer);
	}
	public void selectAccounts(String fromAccountId,String toAccountId) {
		new Select(fromAccount).selectByVisibleText(fromAccountId);
		new Select(toAccount).selectByVisibleText(toAccountId);
	}
	public void hitTransfer() {
		Transfer.click();
	}
	public boolean verifyTransferConfirmation() {
		boolean isTransferConfirmation=false;
		isTransferConfirmation=TransferComplete.isDisplayed();
		return isTransferConfirmation;
	}
}