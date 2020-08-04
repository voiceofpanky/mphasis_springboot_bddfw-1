package com.mphasis.qe.runner;

import org.junit.AfterClass;


import com.mphasis.qe.utils.ReportGenerator;
import com.mphasis.qe.utils.TearDown;
import lombok.extern.slf4j.Slf4j;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@Slf4j
@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/featurefiles" }, glue = { "com.mphasis.qe.utils",
		"com.mphasis.qe.stepdefs" }, tags = { "@web" }, strict = true, monochrome = true, dryRun = false, plugin = {
				"pretty", "json:target/cucumber-reports/cucumber.json",
				"html:target/cucumber-reports/cucumber-html-report",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"com.mphasis.qe.utils.TestCaseListener",

})

public class CucumberRunnerTest {
	@AfterClass
	public static void generateReport() throws Exception {
		System.out.println("After Class start");
		TearDown tearDown = new TearDown(); 
		ReportGenerator reportGenerator = new ReportGenerator();
		reportGenerator.createReport(tearDown.getReportDataList());

		tearDown.quitDriver();

		System.out.println("After Class end");
	}
}