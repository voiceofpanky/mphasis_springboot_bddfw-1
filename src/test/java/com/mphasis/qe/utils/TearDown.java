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
	String scenarioStatus = null;
	ReportData reportdata;

	public TearDown() {

		this.driver = Setup.webdriver;

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
		if (driver != null && scenario.isFailed()) {

			scenarioStatus = scenario.getStatus().toString();
			populateReportDataWeb(scenario);
			saveScreenshotsForScenario(scenario);
		}
		log.info("Closing the app");
	}

	public void populateReportDataWeb(Scenario scenario) {
		reportdata = new ReportData();
		reportdata.setScenarioStatus(scenario.getStatus().toString());
		reportdata.setScenarioFileLocation(scenario.getUri().toString());
		reportdata.setScenarioName(scenario.getName());

	}

	public void populateReportWeb(String exceptionClass) {
		System.out.println("  Exception class:   " + exceptionClass);
		System.out.println("  scenarioStatus   " + scenarioStatus);

		if (scenarioStatus.equalsIgnoreCase("FAILED")) {
			category = Constants.getUIErrorMessage(Constants.getUIErrorMessage(exceptionClass));
		}

		reportdata.setCategory(category);
		reportDataList.add(reportdata);
	}

	public void  quitDriver()
	
	{
		
		driver.quit();
	}

	public List<ReportData> getReportDataList() {
		return reportDataList;
	}

	private void saveScreenshotsForScenario(final Scenario scenario) {

		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png", "ErrorScreenshot" + scenario.getName());
	}
}
