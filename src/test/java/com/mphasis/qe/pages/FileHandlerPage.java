package com.mphasis.qe.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class FileHandlerPage extends BasePage {

    private final String PAGE_URL = "http://zero.webappsecurity.com/login.html";

    @FindBy(id = "user_login")
    private WebElement login;

    @FindBy(id = "user_password")
    private WebElement password;

    @FindBy(name = "submit")
    private WebElement submit;

    public FileHandlerPage() {
        PageFactory.initElements(driver, this);
    }

    public void goToWebAppSecurityPage() {
        driver.get(PAGE_URL);
    }

    public void enterLoginCredentials(String userName, String password) {
        this.login.sendKeys(userName);
        this.password.sendKeys(password);

    }

    public void submit() {
        this.submit.click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

}
