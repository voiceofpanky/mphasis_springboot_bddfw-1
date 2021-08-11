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
        this.userNameTxt.click();
        this.userNameTxt.sendKeys(userName);
    }

    public void enterPassword(String password) {
        this.passwordTxt.click();
        this.passwordTxt.sendKeys(password);
    }

    public Native_DashboardPage login() {
        this.loginBtn.click();
        return dashboardPage;
    }
}

