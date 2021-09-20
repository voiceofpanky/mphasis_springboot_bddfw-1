package com.mphasis.qe.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.mphasis.qe.PropertySourceResolver;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
/****************************************************************************************
 * @author Pankaj Sao : DriverFactory class to return remoteDriver instance of browser
 ****************************************************************************************/
@Slf4j
public class DriverFactory extends RemoteWebDriver {
	@Autowired
    private PropertySourceResolver propertySourceResolver;
	public static String browserStackHostUrl;
	
	
	public RemoteWebDriver driver;
	public synchronized RemoteWebDriver createInstance(String browser)throws Exception {
		if (browser.equalsIgnoreCase("firefox")){        		
			log.info(" Executing on FireFox");
			if(System.getProperty("os.name").contains("Windows")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\library\\geckodriver.exe");
			}else{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/library/geckodriver");
			}

			//System.setProperty("webdriver.gecko.driver",propertySourceResolver.getGeckoDriverPath());
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("marionette", true);
			cap.setBrowserName("firefox");
			cap.setJavascriptEnabled(false);
			driver = new FirefoxDriver(cap);
		} 

		else if (browser.equalsIgnoreCase("chrome")){
			if(System.getProperty("os.name").contains("Windows")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\library\\chrome\\version\\chromedriver.exe");
			}else{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/library/chrome/version/chromedriver");
			}
			//System.setProperty("webdriver.chrome.driver",propertySourceResolver.getChromeDriverPath());
			log.info(" Executing on Chrome");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability("marionette", true);
			cap.setBrowserName("chrome");
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			cap.setPlatform(Platform.ANY);
			driver = new ChromeDriver();							
		} 

		else if (browser.equalsIgnoreCase("ie")){        		
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "\\library\\ie.exe");
			//System.setProperty("webdriver.chrome.driver",propertySourceResolver.getIeDriverPath());
			log.info(" Executing on Internet Explorer");
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			cap.setCapability("marionette", true);
			cap.setBrowserName("internet explorer");
			cap.setPlatform(Platform.ANY);
			cap.setCapability("nativeEvents", false);
			cap.setCapability("unexpectedAlertBehaviour", "accept");
			cap.setCapability("ignoreProtectedModeSettings", true);
			cap.setCapability("disable-popup-blocking", true);
			cap.setCapability("enablePersistentHover", true);
			cap.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(cap);
		} 
		else if (browser.equalsIgnoreCase("edge")){        		
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+ "\\library\\edge.exe");
			//System.setProperty("webdriver.chrome.driver",propertySourceResolver.getEdgeDriverPath());
			log.info(" Executing on Edge browser");
			DesiredCapabilities cap = DesiredCapabilities.edge();
			driver = new EdgeDriver(cap);
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	public synchronized RemoteWebDriver createInstance(String browserName, String browserVersion, String osName,String osVersion,
			String browserStackUsername, String browserStackAccessKey) {
		browserStackHostUrl="https://"+browserStackUsername+":"+browserStackAccessKey+"@hub-cloud.browserstack.com/wd/hub";
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os", osName);
		caps.setCapability("os_version", osVersion);
		caps.setCapability("browser_version", browserVersion);
		caps.setCapability("os", osVersion);
		if (browserName.equalsIgnoreCase("chromeRemote")){
			WebDriverManager.chromedriver().setup();
		}
		else if (browserName.equalsIgnoreCase("firefoxRemote")){
			WebDriverManager.chromedriver().setup();
		}
		else if (browserName.equalsIgnoreCase("edgeRemote")){
			WebDriverManager.chromedriver().setup();
		}
		else if (browserName.equalsIgnoreCase("ieRemote")){
			WebDriverManager.chromedriver().setup();
		}
		try {
			driver=new RemoteWebDriver(new URL(browserStackHostUrl),caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		}
		catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		return driver;
	}

}