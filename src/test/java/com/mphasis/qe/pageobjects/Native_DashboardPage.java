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
public class Native_DashboardPage extends BasePage {
    @iOSXCUITFindBy(accessibility = "Your balance is: ")
    private WebElement balanceLabel;
    @iOSXCUITFindBy(accessibility = "Make Payment")
    private WebElement paymentBtn;
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Mortgage Request' AND name == 'Mortgage Request' AND  type == 'XCUIElementTypeButton'")
    private WebElement mortgageBtn;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Expense Report']")
    private WebElement expenseRptBtn;
    @iOSXCUITFindBy(accessibility = "Logout")
    private WebElement logoutBtn;

    public Native_DashboardPage() {
        PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), this);
    }

    public void logout() throws InterruptedException {
        this.logoutBtn.click();
    }

    public void checkBalanceDisplay() {
        this.balanceLabel.isDisplayed();
    }

    public void navigateTo(String navigateLink) {

        switch (navigateLink) {
            case "Payment":
                this.paymentBtn.click();
                break;
            case "Mortgage":
                this.mortgageBtn.click();
                break;
        }

    }
}
