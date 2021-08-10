package com.mphasis.qe.runner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

/****************************************************************************************
 * @author Pankaj Sao
 ****************************************************************************************/

@CucumberOptions(
		features = {"src/test/resources/featurefiles"},
		glue = {"com.mphasis.qe.utils",
		"com.mphasis.qe.stepdefs"},
		tags = {"@web and @parallel"},
		strict = true,
		monochrome = true,
		dryRun = false,
		plugin = {"pretty",
				"json:target/cucumber-reports/cucumber.json",
				"html:target/cucumber-reports/cucumber-html-report",
		})
public class ParallelRunnerTest extends AbstractTestNGCucumberTests {
	@Parameters({"browser"})
	@Test(groups="cucumber",description="Runs cucumber scenarios", dataProvider="scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable{
		super.runScenario(pickleWrapper, featureWrapper);
	}

	@Override
	@DataProvider(name="scenarios",parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}

