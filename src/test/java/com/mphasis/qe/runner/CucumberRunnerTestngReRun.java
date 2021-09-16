package com.mphasis.qe.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/****************************************************************************************
 * @author Kanhaiya Prasad
 ****************************************************************************************/
@CucumberOptions(
		//features = {"src/test/resources/featurefiles"},
		features ="@target/rerun.txt",
		glue = {"com.mphasis.qe.utils",
		"com.mphasis.qe.stepdefs"},
		tags = "@web",
		strict = true,

		monochrome = true,
		dryRun = false,
		plugin = {"pretty",
				"json:target1/cucumber-reports/cucumber.json",
				"html:target1/cucumber-reports/cucumber-html-report"
		})
public class CucumberRunnerTestngReRun extends AbstractTestNGCucumberTests {
}

