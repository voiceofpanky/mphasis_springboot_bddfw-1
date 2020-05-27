package com.mphasis.qe.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.IntStream;
/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
public class SearchResultPage extends BasePage {

    private static final String RESULTS_TITLE_SELECTOR = "a h3";

    @FindBy(css = RESULTS_TITLE_SELECTOR)
    private List<WebElement> results;

    public SearchResultPage(){
        PageFactory.initElements(driver, this);
    }
    private static final Logger LOGGER = LogManager.getLogger(SearchResultPage.class.getSimpleName());

    public void checkExpectedUrlInResults(String expectedTitle, int nbOfResultsToSearch) {
        wait.forPresenceOfElements(5, By.cssSelector(RESULTS_TITLE_SELECTOR), "Result title");
        Integer indexOfLink = IntStream.range(0, Math.min(this.results.size(), nbOfResultsToSearch))
                .filter(index -> this.results.get(index).getText().contains(expectedTitle))
                .findFirst()
                .orElse(-1);
        Assert.assertNotEquals(!indexOfLink.equals(-1), expectedTitle + " wasn't found in the results.");
    }
}

