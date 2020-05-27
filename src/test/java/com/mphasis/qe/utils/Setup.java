package com.mphasis.qe.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Before;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
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

/**
 * @author : manoj chavan
 * Parent class for all test classes contains basic methods and test case configuration
 */
public class Setup {
    private static final Logger LOGGER = LogManager.getLogger(Setup.class.getSimpleName());
    private static final String APPIUM_WEB_DRIVER_SERVER_URL = "http://localhost:4723/wd/hub";

    AndroidDriver androidDriver;
    IOSDriver iosDriver;

    private Configurations configs = new Configurations();
    public Configuration config;
    private static String operatingsystem;
    private static String browserName;
    public static WebDriver webdriver;

    public Setup(){loadProperties();}

    @Before("@web")
    public void setUp() throws Exception {
        operatingsystem = config.getString("platform.name");
        browserName = config.getString("browser.name");
        LOGGER.info("Setting up WebDriver.");
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps/test.apk");
        if(browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", config.getString("chrome.driver"));
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", config.getString("gecko.driver"));
        }
        else if(browserName.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.ie.driver", config.getString("ie.driver"));
        }
//        this.APPIUM_WEB_DRIVER_SERVER_URL = config.getString("perfecto_url");

        if (operatingsystem.equalsIgnoreCase("android")){
            File app = new File(appDir, config.getString("android.apk.file"));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
            capabilities.setCapability("deviceName",config.getString("device.name"));
            capabilities.setCapability("platformVersion", config.getString("platform.version"));
            capabilities.setCapability("platformName",config.getString("platform.name"));
            capabilities.setCapability("browserName", config.getString("browser.name"));
//            capabilities.setCapability("user", config.getString("perfecto_username"));
//            capabilities.setCapability("password", config.getString("perfecto_password"));
//            capabilities.setCapability("app", app.getAbsolutePath());
//            capabilities.setCapability("autoLaunch", false);
            androidDriver = new AndroidDriver(new URL(APPIUM_WEB_DRIVER_SERVER_URL), capabilities);
            androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            webdriver = androidDriver;
        } else if (operatingsystem.equalsIgnoreCase("ios")){
            File app = new File(appDir, "test.app");
            // Configure the desired capabilities
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "iPhone 6");
            capabilities.setCapability("platformName", "iOS");
//            capabilities.setCapability("user", config.getString("perfecto_username"));
//            capabilities.setCapability("password", config.getString("perfecto_password"));
//   		  capabilities.setCapability("platformVersion", "7.1");
//            capabilities.setCapability("app", app.getAbsolutePath());
            capabilities.setCapability("browserName", config.getString("browser.name"));
//            capabilities.setCapability("autoLaunch", false);
            capabilities.setCapability("launchTimeout", 9000000);

            iosDriver = new IOSDriver(new URL(APPIUM_WEB_DRIVER_SERVER_URL), capabilities);
            iosDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            webdriver = iosDriver;
        } else {
            if(browserName.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized"); // open Browser in maximized mode
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model

                webdriver = new ChromeDriver(options);
            }
            else if(browserName.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("start-maximized"); // open Browser in maximized mode
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model

                webdriver = new FirefoxDriver(options);
            }

            else if(browserName.equalsIgnoreCase("ie")) {
                InternetExplorerOptions options = new InternetExplorerOptions();
                //options.destructivelyEnsureCleanSession();
                options.ignoreZoomSettings();

                webdriver = new InternetExplorerDriver(options);
            }

            webdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            webdriver.manage().deleteAllCookies();
            webdriver.manage().window().maximize();

        }
    }

    private void loadProperties() {
        try {
            config = configs.properties(new File("environment.properties"));
        } catch (ConfigurationException cfx){
            LOGGER.info(cfx.getMessage());
        }
    }
}
