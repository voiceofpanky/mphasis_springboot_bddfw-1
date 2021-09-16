//package com.mphasis.qe.pages;
//
//import lombok.extern.slf4j.Slf4j;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.springframework.stereotype.Component;
///****************************************************************************************
// * @author manoj chavan
// ****************************************************************************************/
//@Slf4j
//@Component
//public class MphasisServiceNowPage extends BasePage {
//    private final String HOME_PAGE_URL = this.baseUrl;
//    private final String PAGE_TITLE = "Self Service Portal - Mphasis SP CIO";
//    private final String LOGIN_TEXT = "//input[@type='email']";
//    private final String NEXT_BTN = "//input[@value = 'Next']";
//    private final String USER_NAME_AREA = "userNameArea";
//    private final String PASSWORD_INPUT = "passwordInput";
//    private final String YES_BTN = "//input[@value = 'Yes']";
//    private final String SIGNIN_BTN = "submitButton";
//
//    //Warning msg
//    private final String warningMsg = "If your Mphasis ID is deactivated, please reach out to your org manager to log a ticket by following navigation path for immediate resolution. NAVIGATION: IT ->  End User Services -> User Account -> Enable user account";
//
//    @FindBy(xpath = LOGIN_TEXT)
//    WebElement login;
//    @FindBy(id = PASSWORD_INPUT)
//    WebElement password;
//    @FindBy(xpath = NEXT_BTN)
//    WebElement nextBtn;
//    @FindBy(xpath = YES_BTN)
//    WebElement yesBtn;
//    @FindBy(id = SIGNIN_BTN)
//    WebElement singIn;
//    public MphasisServiceNowPage() {
//        PageFactory.initElements(driver, this);
//    }
//    public void goToServiceNowPage(){
//        driver.get(HOME_PAGE_URL);
//        this.wait.waitForPageLoaded();
//    }
//    public void clickNextBtn(){
//        this.wait.forElementToBeDisplayed(10, nextBtn,"Next");
//        nextBtn.click();
//        this.wait.waitForPageLoaded();
//    }
//    public void clickYesBtn(){
//        this.wait.forElementToBeDisplayed(10, yesBtn,"Yes");
//        yesBtn.click();
//        this.wait.waitForPageLoaded();
//    }
//    public void setLoginText(String loginText){
//        this.wait.forElementToBeDisplayed(10, login,"Login");
//        login.sendKeys(loginText);
//    }
//    public void setPasswordInput(String pwd){
//        this.wait.forElementToBeDisplayed(10, password,"Password");
//        password.sendKeys(pwd);
//    }
//    public void clickSignIn(){
//        this.wait.forElementToBeDisplayed(10, singIn,"Sign In");
//        singIn.click();
//    }
//    public boolean isWarningMessagePresent(){
//        return this.driver.getPageSource().contains(warningMsg);
//    }
//}
