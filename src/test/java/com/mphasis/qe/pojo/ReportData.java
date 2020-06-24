package com.mphasis.qe.pojo;

import lombok.Data;

@Data
public class ReportData {
	
	String scenarioName;
	String scenarioStatus;
	String scenarioFileLocation;
	String category;
	RequestResponseData data;
	
}
