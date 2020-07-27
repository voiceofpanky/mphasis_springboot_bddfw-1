package com.mphasis.qe.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.mphasis.qe.utils.ReportGenerator;
import com.mphasis.qe.utils.TearDown;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Slf4j
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featurefiles"},
        glue = {"com.mphasis.qe.utils",
               "com.mphasis.qe.stepdefs"},
        tags = {"not @Ignore"},
        //tags = {"@api"},
        strict = false,
        monochrome = true,
        dryRun = false,
        plugin = {"pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber-html-report",
        })
public class CucumberRunnerTest{
	
	 @AfterClass
	 public static void generateReport() throws Exception {
		 TearDown tearDown = new TearDown();
		 ReportGenerator reportGenerator = new ReportGenerator();
		 reportGenerator.createReport(tearDown.getReportDataList());
	 }
}