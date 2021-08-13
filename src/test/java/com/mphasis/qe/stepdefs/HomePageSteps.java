package com.mphasis.qe.stepdefs;

import com.mphasis.qe.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Slf4j
public class HomePageSteps {

    private HomePage homePage;

    public HomePageSteps() {
        this.homePage = new HomePage();
    }

    @Given("A user navigates to HomePage {string}")
    public void aUserNavigatesToHomePage(String country) {
        this.homePage.goToHomePage(country);
    }

    @Then("^Google logo is displayed$")
    public void googleLogoIsDisplayed() {
        this.homePage.checkLogoDisplay();
    }

    @And("^search bar is displayed$")
    public void searchBarIsDisplayed() {
        this.homePage.checkSearchBarDisplay();
    }

    @Then("^page title is \"([^\"]*)\"$")
    public void pageTitleIs(String title) {
        this.homePage.checkTitle(title);
    }

    @When("^a user searches for \"([^\"]*)\"$")
    public void aUserSearchesFor(String searchValue) {
        this.homePage.searchFor(searchValue);
    }

}
