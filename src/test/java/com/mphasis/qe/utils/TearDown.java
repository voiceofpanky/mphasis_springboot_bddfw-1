package com.mphasis.qe.utils;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mphasis.qe.PropertySourceResolver;
import com.mphasis.qe.filter.CustomReportFilter;
import com.mphasis.qe.pojo.ReportData;
import com.mphasis.qe.pojo.RequestResponseData;
import com.mphasis.qe.pojo.WFAutomationTestData;
import com.mphasis.qe.runner.CucumberRunnerTest;
import com.mphasis.qe.utils.Setup;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Slf4j
public class TearDown {

	private WebDriver driver;
	
	Logger log;
	
	@Autowired
	ApiUtil apiUtil;
	@Autowired
	private PropertySourceResolver propertySourceResolver;
	@Autowired
    private ScenarioSession scenarioSession;
	@Autowired
    private DBOperations dbOperations;
	@Autowired
    private WFAutomationTestData wfAutomationTestData;
	
	private static List<ReportData> reportDataList = new ArrayList<>();
    
    public TearDown() {
        this.driver = Setup.webdriver;
    }

    @After("@web")
    public void quitDriver(Scenario scenario){
        if(scenario.isFailed()){
           saveScreenshotsForScenario(scenario);
        }
        log.info("Closing the app");
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
    }
    
    private void saveScreenshotsForScenario(final Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png", "ErrorScreenshot" + scenario.getName());
    }
    
    @After("@api")
    public void quitAPITest(Scenario scenario){
    	CustomReportFilter filter = (CustomReportFilter) apiUtil.getReportFilter();
    	scenario.write(filter.getRequestHooksData() + filter.getResponseHooksData() + "\n");
    	populateReportData(scenario, filter.getRequestResponseData());
    	
    }
    
    public List<ReportData> getReportDataList() {
    	return reportDataList;
    }
    
    public void populateReportData(Scenario scenario, RequestResponseData requestResponseData) {
    	ReportData reportdata = new ReportData();
    	reportdata.setScenarioStatus(scenario.getStatus().toString());
    	reportdata.setScenarioFileLocation(scenario.getUri().toString());
    	reportdata.setScenarioName(scenario.getName());
    	String category = (scenario.getStatus().toString().equals("FAILED")) ? Constants.getHttpMessage(requestResponseData.getResponseStatusCode()) : null;
    	reportdata.setCategory(category);
    	reportdata.setData(requestResponseData);
		reportDataList.add(reportdata);
    }

    @After("@api")
    public void saveDataToTestDB(Scenario scenario) throws JSONException {
      if (!scenario.isFailed() && propertySourceResolver.getDbReport().equalsIgnoreCase("true")) {
    	  wfAutomationTestData.setTestName("Test Scenario Name 123");
    	  wfAutomationTestData.setTestDuration("50");
        dbOperations.insert_into_db(wfAutomationTestData);
      }
    }

    @After
    public void saveToCSVReport(Scenario scenario) throws IOException {
      if (propertySourceResolver.getCsvReport().equalsIgnoreCase("true")) {
        String outputFilePath =
            System.getProperty("user.dir") + "/build/test-results-files/report.csv";
        CSVReport csvReport = new CSVReport(outputFilePath);
        //csvReport.printReport(scenario, scenarioSession, dbOperations);
      }
    }
    
    static String getSystemProperty(String propertyName, String defaultValue) {
        return System.getenv(propertyName) == null
            ? System.getProperty(propertyName, defaultValue)
            : System.getenv(propertyName);
      }
}
