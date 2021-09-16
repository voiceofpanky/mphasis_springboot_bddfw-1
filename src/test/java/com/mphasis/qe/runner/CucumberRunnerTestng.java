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
		tags = "@web and @register",
		//tags = "@ios",
		publish = false,
		strict = true,
		monochrome = true,
		dryRun = false,
		plugin = {"pretty",
				"json:target/cucumber-reports/cucumber.json",
				"html:target/cucumber-reports/cucumber-html-report",
				"rerun:target/rerun.txt"
		})
public class CucumberRunnerTestng extends AbstractTestNGCucumberTests {
}

