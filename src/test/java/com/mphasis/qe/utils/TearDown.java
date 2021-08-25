package com.mphasis.qe.utils;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mphasis.qe.PropertySourceResolver;
import com.mphasis.qe.filter.CustomReportFilter;
import com.mphasis.qe.pojo.ReportData;
import com.mphasis.qe.pojo.RequestResponseData;
//import com.mphasis.qe.pojo.WFAutomationTestData;
import org.apache.logging.log4j.LogManager;
import java.util.Map;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
//@Slf4j
public class TearDown {

	private WebDriver driver;
    private AppiumDriver mobileDriver;
	boolean isParallelCrossbrowser;
	
	Logger log;
	
//	@Autowired
//	ApiUtil apiUtil;
	@Autowired
	private PropertySourceResolver propertySourceResolver;
//	@Autowired
//    private ScenarioSession scenarioSession;
//	@Autowired
//    private DBOperations dbOperations;
//	@Autowired
//    private WFAutomationTestData wfAutomationTestData;
	
	private static List<ReportData> reportDataList = new ArrayList<>();
    
    public TearDown() {
        this.driver = Setup.webdriver;
        this.mobileDriver = Setup.mobileDriver;
    }

    @After("@web")
    public void quitDriver(Scenario scenario){
        if(scenario.isFailed()){
           saveScreenshotsForScenario(scenario);
        }
        isParallelCrossbrowser=propertySourceResolver.isParallelCrossbrowser();
        if(isParallelCrossbrowser) {
        	if(driver!=null) {
        		this.driver.manage().deleteAllCookies();
        	}
        }
        else {
        	this.driver.manage().deleteAllCookies();
        	this.driver.close();
        	this.driver.quit();
        }
    }

    private void saveScreenshotsForScenario(final Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "ErrorScreenshot" + scenario.getName());
    }
    
//    @After("@api")
//    public void quitAPITest(Scenario scenario){
//    	CustomReportFilter filter = (CustomReportFilter) apiUtil.getReportFilter();
//    	scenario.write(filter.getRequestHooksData() + filter.getResponseHooksData() + "\n");
//    	populateReportData(scenario, filter.getRequestResponseData());
//
//    }
    @After("@api")
    public void quitAPITest(Scenario scenario){
        if(scenario.isFailed()) {
            Map.Entry<String, Response> map = ApiUtil.requestResponseMap.entrySet().iterator().next();
            scenario.log("Request: " + map.getKey() + "\n" +
                    "Response:" + map.getValue().asString() + "\n" +
                    "Status Code: " + map.getValue().getStatusCode());
        }
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

//    @After("@api")
//    public void saveDataToTestDB(Scenario scenario) throws JSONException {
//      if (!scenario.isFailed() && propertySourceResolver.getDbReport().equalsIgnoreCase("true")) {
//    	  wfAutomationTestData.setTestName("Test Scenario Name 123");
//    	  wfAutomationTestData.setTestDuration("50");
//        dbOperations.insert_into_db(wfAutomationTestData);
//      }
//    }

//    @After
//    public void saveToCSVReport(Scenario scenario) throws IOException {
//      if (propertySourceResolver.getCsvReport().equalsIgnoreCase("true")) {
//        String outputFilePath =
//            System.getProperty("user.dir") + "/build/test-results-files/report.csv";
//        CSVReport csvReport = new CSVReport(outputFilePath);
//        //csvReport.printReport(scenario, scenarioSession, dbOperations);
//      }
//    }
    
    static String getSystemProperty(String propertyName, String defaultValue) {
        return System.getenv(propertyName) == null
            ? System.getProperty(propertyName, defaultValue)
            : System.getenv(propertyName);
      }

    @After("@native")
    public void tearDownNative(Scenario scenario) throws Exception {
        //log.info("Closing the app");
        this.mobileDriver.quit();

        AppiumServerUtil.stopAppiumServer();


    }
}
