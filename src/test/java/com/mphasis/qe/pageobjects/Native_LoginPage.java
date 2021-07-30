package com.mphasis.qe.pageobjects;

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
    }

    public void checkLogoDisplay() {
        //driver.findElement(By.id("logo.png")).isDisplayed();
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

    public void login() {
        this.loginBtn.click();
    }
}

