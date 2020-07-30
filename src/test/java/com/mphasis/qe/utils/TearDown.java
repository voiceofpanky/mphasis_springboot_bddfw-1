package com.mphasis.qe.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.mphasis.qe.PropertySourceResolver;
import com.mphasis.qe.filter.CustomReportFilter;
import com.mphasis.qe.pojo.ReportData;
import com.mphasis.qe.pojo.RequestResponseData;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.TestCaseFinished;
import lombok.extern.slf4j.Slf4j;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Slf4j
public class TearDown {

	private WebDriver driver;

	@Autowired
	ApiUtil apiUtil;

	@Autowired
	private PropertySourceResolver propertySourceResolver;
	@Autowired
	private TestCaseListener testCaseListener;
	 
	
	private static List<ReportData> reportDataList = new ArrayList<>();
	String category = null;

	public TearDown() {
		
		this.driver = Setup.webdriver;
		System.out.println(driver);
	}

	@After("@api")
	public void quitAPITest(Scenario scenario) {
		CustomReportFilter filter = (CustomReportFilter) apiUtil.getReportFilter();
		scenario.write(filter.getRequestHooksData() + filter.getResponseHooksData() + "\n");
		populateReportDataAPI(scenario, filter.getRequestResponseData());

	}

	public void populateReportDataAPI(Scenario scenario, RequestResponseData requestResponseData) {
		ReportData reportdata = new ReportData();
		reportdata.setScenarioStatus(scenario.getStatus().toString());
		reportdata.setScenarioFileLocation(scenario.getUri().toString());
		reportdata.setScenarioName(scenario.getName());
		category = (scenario.getStatus().toString().equals("FAILED"))
				? Constants.getHttpMessage(requestResponseData.getResponseStatusCode()) : null;
		reportdata.setCategory(category);
		reportdata.setData(requestResponseData);
		reportDataList.add(reportdata);
	}

	@After("@web")
	public void quitDriver(Scenario scenario) {		
		System.out.println("----------TearDown enter-------------");
		String exceptionMessage= null;
		if (driver != null && scenario.isFailed()) {
			saveScreenshotsForScenario(scenario);
		}
		testCaseListener.setScenario(scenario);
		log.info("Closing the app");
		this.driver.manage().deleteAllCookies();
		this.driver.quit();	
		System.out.println("----------TearDown exit-------------");
	}
 
	

	public void populateReportDataWeb(Scenario scenario, String exceptionClass) {
		ReportData reportdata = new ReportData();
		reportdata.setScenarioStatus(scenario.getStatus().toString());
		reportdata.setScenarioFileLocation(scenario.getUri().toString());
		reportdata.setScenarioName(scenario.getName());   
		category = Constants.getUIErrorMessage(exceptionClass);
		reportdata.setCategory(category);
		reportDataList.add(reportdata);
	}

	public List<ReportData> getReportDataList() {
		return reportDataList;
	}

	private void saveScreenshotsForScenario(final Scenario scenario) {

		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png", "ErrorScreenshot" + scenario.getName());
	}
}
