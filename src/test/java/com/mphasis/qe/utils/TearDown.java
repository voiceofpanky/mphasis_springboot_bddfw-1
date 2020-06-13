package com.mphasis.qe.utils;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.mphasis.qe.filter.CustomReportFilter;
import com.mphasis.qe.pojo.ReportData;
import com.mphasis.qe.runner.CucumberRunnerTest;
import com.mphasis.qe.utils.Setup;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Slf4j
public class TearDown {

	private WebDriver driver;
	
	@Autowired
	ApiUtil apiUtil;
	
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
    	scenario.write(filter.getRequestData() + filter.getResponseData() + "\n");
    	populateReportData(scenario, apiUtil.getReportData());
    	
    }
    
    public List<ReportData> getReportDataList() {
    	return reportDataList;
    }
    
    public void populateReportData(Scenario scenario, ReportData data) {
    	data.setScenarioStatus(scenario.getStatus().toString());
    	data.setScenarioFileLocation(scenario.getUri().toString());
    	data.setScenarioName(scenario.getName());
    	String category = (scenario.getStatus().toString().equals("FAILED")) ? CucumberRunnerTest.categoryMap.get(data.getStatusCode()) : null;
    	data.setCategory(category);
		reportDataList.add(data);
    }

}
