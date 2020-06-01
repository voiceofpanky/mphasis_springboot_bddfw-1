package com.mphasis.qe.pageobjects.mphasis;
import com.mphasis.qe.pageobjects.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;
/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Slf4j
@Component
public class MphasisServiceNowPage extends BasePage {
    private final String HOME_PAGE_URL = this.baseUrl;
    private final String PAGE_TITLE = "Self Service Portal - Mphasis SP CIO";
    private final String LOGIN_TEXT = "loginfmt";
    private final String NEXT_BTN = "//input[@value = 'Next']";
    private final String USER_NAME_AREA = "userNameArea";
    private final String PASSWORD_INPUT = "passwordInput";
    private final String YES_BTN = "//input[@value = 'Yes']";
    private final String SIGNIN_BTN = "submitButton";
    @FindBy(id = LOGIN_TEXT)
    WebElement login;
    @FindBy(id = PASSWORD_INPUT)
    WebElement password;
    @FindBy(xpath = NEXT_BTN)
    WebElement nextBtn;
    @FindBy(xpath = YES_BTN)
    WebElement yesBtn;
    @FindBy(id = SIGNIN_BTN)
    WebElement singIn;
    public MphasisServiceNowPage() {
        PageFactory.initElements(driver, this);
    }
    public void goToServiceNowPage(){
        driver.get(HOME_PAGE_URL);
        this.wait.waitForPageLoaded();
    }
    public void clickNextBtn(){
        this.wait.forElementToBeDisplayed(10, nextBtn,"Next");
        nextBtn.click();
        this.wait.waitForPageLoaded();
    }
    public void clickYesBtn(){
        this.wait.forElementToBeDisplayed(10, yesBtn,"Yes");
        yesBtn.click();
        this.wait.waitForPageLoaded();
    }
    public void setLoginText(String loginText){
        this.wait.forElementToBeDisplayed(10, login,"Login");
        login.sendKeys(loginText);
    }
    public void setPasswordInput(String pwd){
        this.wait.forElementToBeDisplayed(10, password,"Password");
        password.sendKeys(pwd);
    }
    public void clickSignIn(){
        this.wait.forElementToBeDisplayed(10, singIn,"Sign In");
        singIn.click();
    }
}
