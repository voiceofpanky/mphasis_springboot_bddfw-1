package com.mphasis.qe.runner;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
//import org.testng.runner.RunWith;
//import io.cucumber.testng.CucumberOptions;

//@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
        retryCount = 3,
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewReport = true,
        //coverageReport = true,
        jsonUsageReport = "target/cucumber-usage.json",
        usageReport = true,
        toPDF = true,
        outputFolder = "target")
@CucumberOptions
        (plugin = { "html:target/cucumber-html-report",
        "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
        "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" },
        features = {"src/test/resources/featurefiles"},
        glue = {"com.mphasis.qe.utils",
                "com.mphasis.qe.stepdefs"},
                tags = {"@web"}
        )
public class CucumberRunnerRetry extends AbstractTestNGCucumberTests {
}

