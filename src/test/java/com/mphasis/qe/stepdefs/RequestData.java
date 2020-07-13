package com.mphasis.qe.stepdefs;

import lombok.Data;

@Data
public class RequestData {
  private String requestType;
  private String url;
  private String parameters;
  private String body;
  private String response;
  private int statusCode;
}
