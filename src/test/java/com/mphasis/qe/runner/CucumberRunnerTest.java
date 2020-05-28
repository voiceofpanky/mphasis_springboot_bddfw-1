package com.mphasis.qe.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featurefiles"},
        glue = {"com.mphasis.qe.utils",
               "com.mphasis.qe.stepdefs"},
        tags = {"not @Ignore"},
        strict = false,
        monochrome = true,
        dryRun = false,
        plugin = {"pretty",
                "json:target/cucumber-reports/cucumber.json",
        })
public class CucumberRunnerTest {
}