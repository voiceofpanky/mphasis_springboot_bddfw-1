package com.mphasis.qe.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.mphasis.qe.pojo.ReportData;
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
        strict = false,
        monochrome = true,
        dryRun = false,
        plugin = {"pretty",
                "json:target/cucumber-reports/cucumber.json",
        })
public class CucumberRunnerTest{
	
	public static Map<Integer, String> categoryMap = new HashMap<Integer, String>();
	
	@BeforeClass
	public static void populateCategoryTypes(){
		categoryMap.put(401, "Access Issue");
		categoryMap.put(403, "Access Issue");
		categoryMap.put(400, "Data Issue");
		categoryMap.put(404, "Data Issue");
		categoryMap.put(415, "Data Issue");
		categoryMap.put(500, "Server Issue");
		categoryMap.put(503, "Server Issue");
		categoryMap.put(504, "Server Issue");		
	}
	
	 @AfterClass
	 public static void createReport() {
		   TearDown tearDown = new TearDown();
		   List<ReportData> reportDataList = tearDown.getReportDataList();
		   reportDataList.forEach(p -> System.out.println(p.toString()));
		   long noOfFailures = reportDataList.stream().filter(p -> p.getScenarioStatus().equals("FAILED")).count();		   
		   long noOfSuccess = reportDataList.stream().filter(p -> p.getScenarioStatus().equals("PASSED")).count();
		   if(noOfFailures != 0) 
		   {
			   StringBuilder htmlStringBuilder=new StringBuilder();
			   
			   htmlStringBuilder.append("<html>");
			   htmlStringBuilder.append("<body><br><br><br>");
			   htmlStringBuilder.append("<h2>Overall Status</h2>");
			   htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\" >");
			   htmlStringBuilder.append("<tr><td bgcolor=\"#00FFFF\"><b>Total Number of Scenarios</b></td><td bgcolor=\"#7FFF00\"><b>No of Passed Scenarios</b></td><td bgcolor=\"#FF6347\"><b>No of Failed Scenarios</b></td></tr>");	
			   htmlStringBuilder.append("<tr><td bgcolor=\"#00FFFF\">" + reportDataList.size() + "</td><td bgcolor=\"#7FFF00\">" + noOfSuccess + "</td><td bgcolor=\"#FF6347\">" + noOfFailures + "</td></tr>");
			   htmlStringBuilder.append("</table><br><br><br>");
			   htmlStringBuilder.append("<h2>Failure Details</h2>");
			   htmlStringBuilder.append("<table bgcolor=\"#FF6347\" border=\"1\" bordercolor=\"#000000\">");
			   htmlStringBuilder.append("<tr><td><b>Scenario Name</b></td><td><b>Failure Category</b></td></tr>");	
			   
			   for (ReportData data : reportDataList) {
				   if(data.getScenarioStatus().toString().equals("FAILED")) {					   		
					   htmlStringBuilder.append("<tr><td>");
					   htmlStringBuilder.append(data.getScenarioName());
					   htmlStringBuilder.append("</td><td>");
					   htmlStringBuilder.append(data.getCategory());
					   htmlStringBuilder.append("</td></tr>");
				   }
			   }
           
			   htmlStringBuilder = htmlStringBuilder.append("</table><br><br><br>");
			   htmlStringBuilder.append("<h2>Failure Count with category</h2>");
			   htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\" bgcolor=\"#FF6347\">");
			   htmlStringBuilder.append("<tr><td><b>Failure Category</b></td><td><b>Count</b></td></tr>");
			   
			   Map<String, Long> categoryCount = reportDataList.stream()
							.filter(p->p.getCategory()!=null)
							.collect(Collectors.groupingBy(ReportData::getCategory,Collectors.counting()));
			   
			   for (Map.Entry<String, Long> entry : categoryCount.entrySet()) {
				   htmlStringBuilder.append("<tr><td>" + entry.getKey() + "</td><td>" + entry.getValue() + "</td></tr>");
			   }
			   
			   htmlStringBuilder.append("</table></body></html>");
			   String projectDir = System.getProperty("user.dir");
			   File newHtmlFile = new File(projectDir
		                            + "/target/cucumber-reports/cucumber-html-reports/Failure_category.html");
			   try {
				   	FileUtils.writeStringToFile(newHtmlFile, htmlStringBuilder.toString());
			   		} 
			   catch (IOException e) {
			   		e.printStackTrace();
			   }
		   }else {
			   log.info("Failure report is not generated as there are no failures");
		   }
		
	   }
}