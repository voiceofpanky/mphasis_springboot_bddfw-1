package com.mphasis.qe.utils;

 
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class PropertiesAPI {

	  private String masterItemServiceHost;
	  @Value("${pemservice.host}")
	  private String pemServiceHost;
	  @Value("${planningservice.host}")
	  private String planningServiceHost;
	  @Value("${prepackservice.host}")
	  private String prepackServiceHost;
	  @Value("${productfoundationservice.host}")
	  private String productFoundationServiceHost;
	  @Value("${promptadapterservice.host}")
	  private String promptAdapterServiceHost;
	  @Value("${receiptmanagerservice.host}")
	  private String receiptManagerServiceHost;
	  @Value("${receiptservice.host}")
	  private String receiptServiceHost;
	  @Value("${retailstyleservice.host}")
	  private String retailStyleServiceHost;
	  @Value("${rscservice.host}")
	  private String rscServiceHost;
	  @Value("${spexservice.host}")
	  private String spexServiceHost;
	  @Value("${trainingheader}")
	  private boolean trainingheader;
	  @Value("${requireReport}")
	  private boolean requireReport;
	 
}
