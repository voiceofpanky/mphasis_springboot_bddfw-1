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
public class Native_PaymentPage extends BasePage {
    @iOSXCUITFindBy(accessibility = "phoneTextField")
    private WebElement phoneTxt;
    @iOSXCUITFindBy(accessibility = "nameTextField")
    private WebElement nameTxt;
    @iOSXCUITFindBy(accessibility = "amountTextField")
    private WebElement amountTxt;
    @iOSXCUITFindBy(accessibility = "countryButton")
    private WebElement countryBtn;
    @iOSXCUITFindBy(accessibility = "sendPaymentButton")
    private WebElement sendPayBtn;
    @iOSXCUITFindBy(accessibility = "cancel")
    private WebElement cancelBtn;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/*/XCUIElementTypeStaticText[@name='India']")
    private WebElement tableViewIndia;
    @iOSXCUITFindBy(accessibility = "Yes")
    private WebElement confirmYesBtn;
    @iOSXCUITFindBy(accessibility = "No")
    private WebElement confirmNoBtn;

    public Native_PaymentPage() {
        PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), this);
    }

    public void getCountrySelected(String country) {
        this.countryBtn.click();
        this.tableViewIndia.isDisplayed();
        this.tableViewIndia.click();
    }

    public void sendAction(String actionButton) {
        switch (actionButton) {
            case "Payment":
                this.sendPayBtn.click();
                break;
            case "Cancel":
                this.cancelBtn.click();
                break;
            case "Yes":
                this.confirmYesBtn.click();
                break;
            case "No":
                this.confirmNoBtn.click();
                break;
        }

    }

    public void sendText(String element, String value) {
        switch (element) {
            case "Amount":
                this.amountTxt.click();
                this.amountTxt.sendKeys(value);
                break;
            case "PhoneNumber":
                this.phoneTxt.click();
                this.phoneTxt.sendKeys(value);
                break;
            case "Name":
                this.nameTxt.click();
                this.nameTxt.sendKeys(value);
                break;
        }
    }

}
