package com.mphasis.qe.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mphasis.qe.pojo.WFAutomationTestData;
import com.mphasis.qe.repository.WFAutomationTestDataRepository;

@Component
public class DBOperations {

	@Autowired
	WFAutomationTestDataRepository wfAutomationTestDataRepository;
	
//	@Autowired
//	WFAutomationTemplateRepository wfAutomationTemplateRepository;
	
	public void insert_into_db(WFAutomationTestData dataObj) {
		wfAutomationTestDataRepository.save(dataObj);
	}

}
