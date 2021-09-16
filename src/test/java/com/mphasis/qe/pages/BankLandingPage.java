package com.mphasis.qe.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/
public class BankLandingPage extends BasePage {

	    @FindBy(xpath ="//a[contains(text(),'Transfer Funds')]")
	    public WebElement transferFunds;
	    
	    @FindBy(css ="input[name='input']")
	    private WebElement amount;
	  
	    @FindBy(css ="#fromAccountId")
	    private WebElement fromAccount;
	    
	    @FindBy(css ="#toAccountId")
	    private WebElement toAccount;
	    
	    @FindBy(css = "input[type='submit']")
	    public WebElement transfer;
	    
	    @FindBy(xpath ="//h1[contains(text(),'Transfer Complete')]")
	    public WebElement transferComplete;
	    
	    @FindBy(xpath ="//h1[contains(text(),'Accounts Overview')]")
	    private WebElement accountOverview;
    
	    @FindBy(xpath ="//h1[contains(text(),'Welcome')]")
	    public WebElement welcomeGreeting;
	    
    public BankLandingPage() {
        PageFactory.initElements(driver, this);
    }
    
	public boolean verifyAcountOverview() {
		boolean isDisplayed=false;
		wait.forElementToBeDisplayed(10, this.accountOverview, "Account Overview");
		isDisplayed=accountOverview.isDisplayed();
		return isDisplayed;
	}
	
	public void enterAmount(String amountToTransfer) {
		amount.sendKeys(amountToTransfer);
	}
	
	public void selectAccounts(String fromAccountId,String toAccountId) {
		new Select(fromAccount).selectByIndex(0); //default index values available in drop down
		new Select(toAccount).selectByIndex(0); //default index values available in drop down
	}

	public boolean verifyTransferConfirmation() {
		boolean isTransferConfirmation=false;
		wait.forElementToBeDisplayed(10, this.transferComplete, "Transfer Complete");
		isTransferConfirmation=transferComplete.isDisplayed();
		return isTransferConfirmation;
	}
}