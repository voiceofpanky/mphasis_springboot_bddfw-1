package com.mphasis.qe.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/
public class BankLoginPage extends BasePage {

	
    private final String PAGE_URL = this.bankUrl;
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
    }
    public void gotoBankHomepage() {
    	 driver.get(PAGE_URL);
    	 wait.forLoading(5);
    }
    
    public BankLandingPage login(){
    	 bankLandingPage=new BankLandingPage();
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
}