package com.mphasis.qe.utils;

import org.springframework.stereotype.Component;

import io.cucumber.java.Scenario;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;

@Component
public class TestCaseListener implements EventListener {

	 
	TearDown tear = new TearDown();

	 

	@Override
	public void setEventPublisher(final EventPublisher publisher) {
		publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
		publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
	}

	public void onTestCaseStarted(TestCaseStarted event) {
		TestCase testCase = event.getTestCase();
		System.out.println("Starting " + testCase.getName());
	}

	private void onTestCaseFinished(final TestCaseFinished event) {
		TestCase testCase = event.getTestCase();
		System.out.println("Finished " + testCase.getName());

		Result result = event.getResult();
		if (result.getStatus() == Status.FAILED) {
			final Throwable error = result.getError();
			String exceptionMessage = error.getClass().getName();
			tear.populateReportWeb(exceptionMessage);

		}
		 
		tear.quitDriver();

	}

	 
}
