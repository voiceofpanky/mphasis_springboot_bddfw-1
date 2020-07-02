package com.mphasis.qe.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.mphasis.qe.pojo.ReportData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportGenerator {
	
	 String passedScenario =
	          "<div class=\"alert alert-dark text-uppercase iteration-0 bg-success \">\n"
	                  + "        <a data-toggle=\"collapse\" href=\"#\" data-target=\"#folder-collapse-$Index\" aria-expanded=\"false\" aria-controls=\"collapse\" id=\"folder-$Index\" class=\"collapsed text-white z-block\">\n"
	                  + "                $ScenarioName <i class=\"float-lg-right fa fa-chevron-down\"></i>\n"
	                  + "        </a>\n"
	                  + "        </div>";
	 
	  String passedStep =
	          " <div id=\"folder-collapse-$Index\" class=\"collapse\" aria-labelledby=\"folder-$Index\">\n"
	                  + "            <div id=\"folder-$Index1\" class=\"card-deck iteration-0\">\n"
	                  + "            <div class=\"row iteration-0\">\n"
	                  + "                <div class=\"col-sm-12 mb-3 iteration-0\">\n"
	                  + "                <div class=\"card iteration-0\">\n"
	                  + "                    <div class=\"card-header  bg-info iteration-0\">\n"
	                  + "                        <a data-toggle=\"collapse\" href=\"#\" data-target=\"#collapse-$Index1\" aria-expanded=\"false\" aria-controls=\"collapse\" id=\"requests-$Index1\" class=\"collapsed text-white z-block\">\n"
	                  + "                                $StepName <i class=\"float-lg-right fa fa-chevron-down\"></i>\n"
	                  + "                </a>\n"
	                  + "                    </div>\n"
	                  + "                    <div id=\"collapse-$Index1\" class=\"collapse\" aria-labelledby=\"requests-$Index1\">\n"
	                  + "                    <div class=\"card-body\">\n"
	                  + "                        <div class=\"row\">\n"
	                  + "                            <div class=\"col-sm-12 mb-3\">\n"
	                  + "                                <div class=\"card-deck\">\n"
	                  + "                                <div class=\"card border-info\" style=\"width: 50rem;\">\n"
	                  + "                                    <div class=\"card-body\">\n"
	                  + "                                    <h5 class=\"card-title text-uppercase text-white text-center bg-info\">Request Information</h5>\n"
	                  + "                                    <span><i class=\"fas fa-info-circle\"></i></span><strong> Request Method:</strong> <span class=\"badge-outline-success badge badge-success\"> $RequestMethod</span><br>\n"
	                  + "                                    <span><i class=\"fas fa-info-circle\"></i></span><strong> Request URL:</strong> <a href=\"$RequestURL\" target=\"_blank\">$RequestURL</a><br>\n"
	                  + "                                    <span><i class=\"fas fa-info-circle\"></i></span><strong> Request Parameters:</strong> <div>$RequestParameters</div><br>\n"
	                  + "                                    </div>\n"
	                  + "                                </div>\n"
	                  + "                                <div class=\"card border-info\" style=\"width: 50rem;\">\n"
	                  + "                                    <div class=\"card-body\">\n"
	                  + "                                    <h5 class=\"card-title text-uppercase text-white text-center bg-info\">Response Information</h5>\n"
	                  + "                                    <span><i class=\"fas fa-info-circle\"></i></span><strong> Response Code:</strong> <span class=\"float-right badge-outline badge badge-success\"> $ResponseStausCode</span><br>\n"
	                  + "                                    <hr>\n"
	                  + "                                   \n"
	                  + "                                    </div>\n"
	                  + "                                </div>\n"
	                  + "                            </div>\n"
	                  + "                            </div>\n"
	                  + "                        </div>\n"
	                  + "                       \n"
	                  + "                        <div class=\"row\">\n"
	                  + "                        <div class=\"col-sm-12 mb-3\">\n"
	                  + "                            <div class=\"card-deck\">\n"
	                  + "                            <div class=\"card border-info\" style=\"width: 50rem;\">\n"
	                  + "                                <div class=\"card-body\">\n"
	                  + "                                    <h5 class=\"card-title text-uppercase text-white text-center bg-info\">Request Info</h5>\n"
	                  + "                                        <div class=\"dyn-height\">\n"
	                  + "                                            <pre><code id=\"RequestBody\">$RequestBody</code></pre>\n"
	                  + "                                        </div>\n"
	                  + "                                </div>\n"
	                  + "                            </div>\n"
	                  + "                            </div>\n"
	                  + "                        </div>\n"
	                  + "                        </div>\n"
	                  + "                        <div class=\"row\">\n"
	                  + "                        <div class=\"col-sm-12 mb-3\">\n"
	                  + "                            <div class=\"card-deck\">\n"
	                  + "                            <div class=\"card border-info\" style=\"width: 50rem;\">\n"
	                  + "                                <div class=\"card-body\">\n"
	                  + "                                    <h5 class=\"card-title text-uppercase text-white text-center bg-info\">Response Info</h5>\n"
	                  + "                                        <div class=\"dyn-height\">\n"
	                  + "                                                <pre>\n"
	                  + "                                                    <code id=\"copyText-28264c78-6f75-4b52-8b8a-be5cde9c1eff\" class=\"prettyPrint\">\n"
	                  + "                                                        $ResponseBody\n"
	                  + "                                                        </code></pre>\n"
	                  + "                                        </div>\n"
	                  + "                                        <div class=\"card-footer\">\n"
	                  + "                                            <button class=\"btn btn-outline-secondary btn-sm copyButton\" type=\"button\" data-clipboard-action=\"copy\" data-clipboard-target=\"#copyText-28264c78-6f75-4b52-8b8a-be5cde9c1eff\">Copy to Clipboard</button>\n"
	                  + "                                        </div>\n"
	                  + "                                </div>\n"
	                  + "                            </div>\n"
	                  + "                            </div>\n"
	                  + "                            </div>\n"
	                  + "                        </div>\n"
	                  + "                                  \n"
	                  + "                    </div>\n"
	                  + "                    </div>\n"
	                  + "                </div>      \n"
	                  + "                </div>\n"
	                  + "            </div>\n"
	                  + "            </div>   \n"
	                  + "        </div>";

	  public void createReport(List<ReportData> reportDataList) throws Exception 
	  {
	    String projectDir = System.getProperty("user.dir");
	    File input = new File(projectDir + "/Report/Report_Template.html");
	    Document doc = Jsoup.parse(input, "UTF-8");
	    String failedScenario="";
	    Map<String, Long> categoryCount = reportDataList.stream()
					.filter(p->p.getCategory()!=null)
					.collect(Collectors.groupingBy(ReportData::getCategory,Collectors.counting()));

	    if(!categoryCount.isEmpty()) {
	    	StringBuilder htmlStringBuilder=new StringBuilder();
			   htmlStringBuilder.append("<html>");
			   htmlStringBuilder.append("<body><br>");
			   htmlStringBuilder.append("<table bgcolor=\"#8393BB\" style=\"border:5px double black;\">");
			   htmlStringBuilder.append("<tr><td style=\"border:5px double black;\"><b>Failure Category </b></td><td style=\"border:5px double black;\"><b>Count </b></td></tr>");
        
			   for (Map.Entry<String, Long> entry : categoryCount.entrySet()) {
				   htmlStringBuilder.append("<tr><td style=\"border:5px double black;\">" + entry.getKey() + "</td><td style=\"border:5px double black;\">" + entry.getValue() + "</td></tr>");
			   }
        
			   htmlStringBuilder.append("</table><br><br></body></html>"); 
			   htmlStringBuilder.append("<table bgcolor=\"#8393BB\" style=\\\"border:5px double black;\\\">");
			   htmlStringBuilder.append("<tr><td style=\"border:5px double black;\"><b>Scenario Name</b></td><td style=\"border:5px double black;\"><b>Failure Category</b></td></tr>");	
			   
			   for (ReportData data : reportDataList) {
				   if(data.getScenarioStatus().toString().equals("FAILED")) {					   		
					   htmlStringBuilder.append("<tr><td style=\"border:5px double black;\">");
					   htmlStringBuilder.append(data.getScenarioName());
					   htmlStringBuilder.append("</td><td style=\"border:5px double black;\">");
					   htmlStringBuilder.append(data.getCategory());
					   htmlStringBuilder.append("</td></tr>");
				   }
			   }
           
			   htmlStringBuilder.append("</table><br><br><br>");
			   doc.getElementsByAttributeValue("Name", "CategoryTab").append(htmlStringBuilder.toString());
	    }
	    int testCaseId = 1;
	    for (ReportData reportData : reportDataList) {
	    	String scenarioName = passedScenario.replaceAll("\\$ScenarioName", reportData.getScenarioName())
	                      						.replaceAll("\\$Index", "a" + Integer.toString(testCaseId));
	    	String LastRequest = "";
	    	int stepId = 1;
	    	try 
	    	{
	    		if (LastRequest.equals(reportData.getData().getRequestUri())) continue;
	    		else LastRequest = reportData.getData().getRequestUri();
	    		String response = (reportData.getData().getResponseBody() == null) ? "" : CreateBody(reportData.getData().getResponseBody());
	    		String request = passedStep.replaceAll("\\$Index1", Integer.toString(stepId))
	    								   .replaceAll("\\$Index", "a" + Integer.toString(testCaseId))
	    								   .replaceAll("\\$StepName",
	    										   	   "REQUEST NO "
	    										   		+ Integer.toString(stepId++)
	    										   		+ "( "
	    										   		+ reportData.getData().getRequestUri()
	    										   		+ " )")
	    								   .replaceAll("\\$RequestMethod", reportData.getData().getRequestMethod())
	    								   .replaceAll("\\$RequestURL", reportData.getData().getRequestUri())
	    								   .replace("$ResponseBody", response)
	    								   .replaceAll("\\$ResponseStausCode", Integer.toString(reportData.getData().getResponseStatusCode()))
	    								   .replace("$RequestBody", (reportData.getData().getRequestBody() == null) ? "" : reportData.getData().getRequestBody())
	    								   .replace("$RequestParameters",(reportData.getData().getRequestParams() == null) ? "" : reportData.getData().getRequestParams());
	    		if (reportData.getScenarioStatus().contains("FAILED")) {
		            scenarioName = scenarioName.replaceAll("bg-success", "bg-danger");
		            failedScenario = scenarioName + request;
		        }
	    		scenarioName = scenarioName + request;
	        } catch (Exception e) {
	          log.info(reportData.getScenarioName());
	          e.printStackTrace();
	        }
	      testCaseId++;
	      try {
	        doc.getElementsByAttributeValue("Name", "RequestsTab").append(scenarioName);
	        doc.getElementsByAttributeValue("Name", "CategoryTab").append(failedScenario);
	      } catch (Exception e) {
	        String PassedScenario =
	                "<div class=\"alert alert-dark text-uppercase text-center iteration-0 bg-success \">\n"
	                        + "        <a data-toggle=\"collapse\" href=\"#\" data-target=\"#folder-collapse-$Index\" aria-expanded=\"false\" aria-controls=\"collapse\" id=\"folder-$Index\" class=\"collapsed text-white z-block\">\n"
	                        + reportData.getScenarioName()
	                        + "  ( Due to size limit response is not printing )  <i class=\"float-lg-right fa fa-chevron-down\"></i>\n"
	                        + "        </a>\n"
	                        + "        </div>";
	        doc.getElementsByAttributeValue("Name", "RequestsTab").append(scenarioName);
	      }
	    }
	    String noOfFailures = String.valueOf(reportDataList.stream().filter(p -> p.getScenarioStatus().equals("FAILED")).count());		   
	    String noOfSuccess = String.valueOf(reportDataList.stream().filter(p -> p.getScenarioStatus().equals("PASSED")).count());
	    String s = doc.html();
	    s = s.replaceAll("\\$Total", Integer.toString(reportDataList.size()))
	                    .replaceAll("\\$Passed", noOfSuccess)
	                    .replaceAll("\\$Failed", noOfFailures);
	    File newHtmlFile = new File(projectDir + "/Report/Failure_category.html");
	    FileWriter writer = new FileWriter(newHtmlFile);
	    writer.write(s);
	    writer.close();
	  }

	  private String CreateBody(String s) {
	    try {
	      if (!s.contains("},{")) return s;
	      boolean arrayJSON = false;
	      JSONObject obj, Tempobj;
	      if (s.trim().startsWith("[")) {
	        arrayJSON = true;
	        if (s.length() > 2 && s.contains(":")) {
	          obj = new JSONObject(new JSONArray(s).get(0).toString());
	          Tempobj = new JSONObject(new JSONArray(s).get(0).toString());
	        } else return s;
	      } else {
	        obj = new JSONObject(s);
	        Tempobj = new JSONObject(s);
	      }

	      for (Iterator it = obj.keys(); it.hasNext(); ) {
	        String key = it.next().toString();
	        String json = Tempobj.get(key).toString();
	        boolean temp = false;
	        if (json.startsWith("[") && json.length() > 2) {
	          temp = true;
	          json = "[" + new JSONArray(json).get(0).toString() + "]";
	        }
	        if ((json.startsWith("{") || json.startsWith("[")) && json.length() > 2) {
	          while (json.contains("},{")) {
	            json = CreateBody(json);
	          }

	          if (temp == false) {
	            Tempobj.remove(key);
	            Tempobj.put(key, new JSONObject(json));
	          } else {
	            Tempobj.remove(key);
	            Tempobj.put(key, new JSONArray(json));
	          }
	        }
	      }
	      String trimmedJSON = arrayJSON ? "[" + Tempobj.toString() + "]" : Tempobj.toString();
	      return trimmedJSON;
	    } catch (Exception e) {
	      return s;
	    }
	  }
	}