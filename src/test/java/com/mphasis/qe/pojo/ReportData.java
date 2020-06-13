package com.mphasis.qe.pojo;

import lombok.Data;

@Data
public class ReportData {

	int statusCode;
	String category;
	String scenarioName;
	String scenarioStatus;
	String scenarioFileLocation;
}
