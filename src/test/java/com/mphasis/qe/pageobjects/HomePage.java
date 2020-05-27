package com.mphasis.qe.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Component
public class HomePage extends BasePage{

    private static final String HOME_PAGE_URL = "https://www.google.";

    @FindBy(css = "#hplogo")
    private WebElement logo;

    @FindBy(css = "input[name=q]")
    private WebElement searchInput;


    public HomePage() {
        PageFactory.initElements(driver, this);
    }
    private static final Logger LOGGER = LogManager.getLogger(HomePage.class.getSimpleName());
    public void goToHomePage(String country){
        LOGGER.debug(country);
        driver.get(HOME_PAGE_URL + country);
        wait.forLoading(5);
    }

    public void checkLogoDisplay() {
        wait.forElementToBeDisplayed(5, this.logo, "Logo");
    }

    public void checkTitle(String title) {
        String displayedTitle = driver.getTitle();
        Assert.assertEquals(title, displayedTitle);
    }

    public void checkSearchBarDisplay() {
        wait.forElementToBeDisplayed(10, this.searchInput, "Search Bar");
    }

    public void searchFor(String searchValue) {
        this.searchInput.sendKeys(searchValue);
        this.searchInput.sendKeys(Keys.ENTER);
    }
}
