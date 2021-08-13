package com.mphasis.qe.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/****************************************************************************************
 * @author Pankaj Sao : BankRegistration PageObject for registration form
 ****************************************************************************************/
public class BankRegistrationPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Register')]")
    public WebElement linkRegister;
    
    @FindBy(css = "input[name='customer.firstName']")
    public WebElement textfirstName;

    @FindBy(css = "input[name='customer.lastName']")
    public WebElement textlastName;
    
    @FindBy(css = "input[name='customer.address.street']")
    public WebElement textAddress;
    
    @FindBy(css = "input[name='customer.address.city']")
    public WebElement textCity;		
    		
    @FindBy(css = "input[name='customer.address.state']")
    public WebElement textState;
    
    @FindBy(css = "input[name='customer.address.zipCode']")
    public WebElement textZipCode;	
    
    @FindBy(css = "input[name='customer.phoneNumber']")
    public WebElement textPhone;			
    
    @FindBy(css = "input[name='customer.ssn']")
    public WebElement textSsn;					
    
    @FindBy(css = "input[name='customer.username']")
    public WebElement textUsername;					
    
    @FindBy(css = "input[name='customer.password']")
    public WebElement textPassword;				
    
    @FindBy(css = "input[name='repeatedPassword']")
    public WebElement textrepeatPassword;	
    
    @FindBy(css = "input[value='Register']")
    public WebElement btnRegister;		
    
    public BankRegistrationPage() {
        PageFactory.initElements(driver, this);
    }
    
}