package com.mphasis.qe.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mphasis.qe.PropertySourceResolver;
import org.apache.logging.log4j.util.PropertySource;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    List<String> loginInfo = new ArrayList<>();

    /**
     * This Method will be used read excel and json file
     */
    public List<String> readFile(String fileType, String filePath) {

        if (fileType.equalsIgnoreCase("excel")) {
            File file = new File(filePath + "details.xlsx");
            loginInfo = excelReader(file);
        } else {
            loginInfo = jsonReader(filePath + "userInfo.json");
        }

        return loginInfo;

    }

    /**
     * This Method will be used to read excel file
     */
    public List<String> excelReader(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                for (int c = 0; c < row.getLastCellNum(); c++) {
                    loginInfo.add(String.valueOf(row.getCell(c)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginInfo;
    }

    /**
     * This method will be used to json file
     */
//    public List<String> jsonReader(String filePath) {
//        List<String> loginInfo = new ArrayList<>();
//
//        JSONParser parser = new JSONParser();
//        try {
//            Object obj = parser.parse(new java.io.FileReader(filePath));
//
//            JSONObject jsonObject = (JSONObject) obj;
//
//            loginInfo.add((String) jsonObject.get("username"));
//            loginInfo.add((String) jsonObject.get("password"));
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return loginInfo;
//    }

    /**
     * This method will be used to json file parsing
     */
    public List<String> jsonReader(String filePath) {
        List<String> loginInfo = new ArrayList<>();
        try {
            JsonElement jelement = new JsonParser().parse(new java.io.FileReader(filePath));

            JsonObject jobject = jelement.getAsJsonObject();

            loginInfo.add((String) jobject.get("username").getAsString());
            loginInfo.add((String) jobject.get("password").getAsString());
        }catch (IOException  e) {
            e.printStackTrace();
            return null;
        }

        return loginInfo;
    }
}