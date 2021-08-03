package com.mphasis.qe.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/

@CucumberOptions(
		features = {"src/test/resources/featurefiles"},
		glue = {"com.mphasis.qe.utils",
		"com.mphasis.qe.stepdefs"},
		tags = {"@web and @fundTransfer"},
		strict = true,
		monochrome = true,
		dryRun = false,
		plugin = {"pretty",
				"json:target/cucumber-reports/cucumber.json",
				"html:target/cucumber-reports/cucumber-html-report",
		})
public class CucumberRunnerTestng extends AbstractTestNGCucumberTests {
}

