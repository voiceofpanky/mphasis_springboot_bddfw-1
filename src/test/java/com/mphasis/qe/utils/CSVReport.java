package com.mphasis.qe.utils;

//import com.mphasis.qe.utils.DBOperations;

import io.cucumber.java.Scenario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVReport {

  private String outputFilePath;

  public CSVReport(String outputFilePath) {
    this.outputFilePath = outputFilePath;
  }

  private void saveToCSV(final Object... values) throws IOException {
    CSVPrinter csvPrinter = this.createFile();
    csvPrinter.printRecord(values);
    csvPrinter.flush();
  }

  public void printReport(
      //Scenario scenario, ScenarioSession scenarioSession, DBOperations dbOperations)
      Scenario scenario, ScenarioSession scenarioSession)
      throws IOException {
    saveToCSV(
        scenario.getName(),
        scenario.isFailed() ? "Failed" : "Passed");//,
        //scenarioSession.getHost());
  }

  /**
   * createFile contains the required header fields required for the CSVReport. The objects must be
   * sent in the order set in this method.
   */
  private CSVPrinter createFile() throws IOException {
    CSVFormat csvFormat =
        CSVFormat.EXCEL.withHeader(
            "scenarioName",
            "scenarioStatus",
            "host");
    	if (Files.exists(Paths.get(outputFilePath))) 
    		csvFormat = csvFormat.withSkipHeaderRecord();
    	Writer csvFile = new BufferedWriter(new FileWriter(outputFilePath, true));
    	return new CSVPrinter(csvFile, csvFormat);
  }
}
