package com.mphasis.qe.runner;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/

@CucumberOptions(
		features = {"src/test/resources/featurefiles"},
		glue = {"com.mphasis.qe.utils",
		"com.mphasis.qe.stepdefs"},
		tags = "@web and @parallel",
		strict = true,
		monochrome = true,
		publish = true,
		dryRun = false,
		plugin = {"pretty",
				"json:target/cucumber-reports/cucumber.json",
				"html:target/cucumber-reports/cucumber-html-report"
		})
@Parameters({"browser"})
@Test
public class ParallelRunnerTest extends AbstractTestNGCucumberTests {
}

