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

        })
public class CucumberRunnerTest{
	
	public static Map<Integer, String> categoryMap = new HashMap<Integer, String>();	
	
	@BeforeClass
	public static void populateCategoryTypes(){
		categoryMap.put(401, "Access Issue");
		categoryMap.put(403, "Bad Request");
		categoryMap.put(400, "Data Issue");
		categoryMap.put(404, "Data Issue");
		categoryMap.put(415, "Data Issue");
		categoryMap.put(500, "Server Issue");
		categoryMap.put(503, "Server Issue");
		categoryMap.put(504, "Server Issue");		
		
	}
	
	 @AfterClass
	 public static void generateReport() throws Exception {
		 TearDown tearDown = new TearDown();
		 ReportGenerator reportGenerator = new ReportGenerator();
		 reportGenerator.createReport(tearDown.getReportDataList());
	 }
}