package com.mphasis.qe.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/
public class BankLandingPage extends BasePage {

	 @FindBy(xpath ="//a[contains(text(),'Transfer Funds')]")
	    private WebElement transferFunds;
	    
	    @FindBy(css ="input[name='input']")
	    private WebElement amount;
	  
	    @FindBy(css ="#fromAccountId")
	    private WebElement fromAccount;
	    
	    @FindBy(css ="#fromAccountId")
	    private WebElement toAccount;
	    
	    @FindBy(css = "input[type='submit']")
	    private WebElement transfer;
	    
	    @FindBy(xpath ="//h1[contains(text(),'Transfer Complete')]")
	    private WebElement transferComplete;
	    
	    @FindBy(xpath ="//h1[contains(text(),'Accounts Overview')]")
	    private WebElement accountOverview;
    
    public BankLandingPage() {
        PageFactory.initElements(driver, this);
    }
    
	public boolean verifyAcountOverview() {
		boolean isDisplayed=false;
		isDisplayed=accountOverview.isDisplayed();
		return isDisplayed;
	}
    
	public void navigateToTransferFund() {
		transferFunds.click();
	}
	
	public void enterAmount(String amountToTransfer) {
		amount.sendKeys(amountToTransfer);
	}
	public void selectAccounts(String fromAccountId,String toAccountId) {
		new Select(fromAccount).selectByIndex(0); //default index values available in drop down
		new Select(toAccount).selectByIndex(0); //default index values available in drop down
	}
	public void hitTransfer() {
		transfer.click();
	}
	public boolean verifyTransferConfirmation() {
		boolean isTransferConfirmation=false;
		isTransferConfirmation=transferComplete.isDisplayed();
		return isTransferConfirmation;
	}
}