package com.mphasis.qe.runner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
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
public class CucumberRunnerTest {
}