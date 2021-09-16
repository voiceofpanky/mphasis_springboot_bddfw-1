package com.mphasis.qe.pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

/****************************************************************************************
 * @author Tamilselvan Ramalingam
 ****************************************************************************************/
@Slf4j
@Component
public class Native_LoginPage extends BasePage {
    private Native_DashboardPage dashboardPage;

    @iOSXCUITFindBy(accessibility = "logo.png")
    private WebElement logoImg;

    @iOSXCUITFindBy(accessibility = "usernameTextField")
    private WebElement userNameTxt;

    @iOSXCUITFindBy(accessibility = "passwordTextField")
    private WebElement passwordTxt;

    @iOSXCUITFindBy(accessibility = "loginButton")
    private WebElement loginBtn;

    public Native_LoginPage() {
        PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), this);
        this.dashboardPage = new Native_DashboardPage();
    }

    public void checkLogoDisplay() {
          this.logoImg.isDisplayed();
    }

    public void enterUserName(String userName) {
        log.info("Entering the enterUserName step");
        this.userNameTxt.click();
        this.userNameTxt.sendKeys(userName);
        log.info("Exiting the enterUserName step");
    }

    public void enterPassword(String password) {
        log.info("Entering the enterPassword step");
        this.passwordTxt.click();
        this.passwordTxt.sendKeys(password);
        log.info("Exiting the enterPassword step");
    }

    public Native_DashboardPage login() {

        log.info("Entering the login step");
        this.loginBtn.click();

        log.info("Exiting the login step");
        return dashboardPage;
    }
}

