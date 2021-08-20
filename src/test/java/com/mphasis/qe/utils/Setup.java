package com.mphasis.qe.utils;

import com.mphasis.qe.PropertySourceResolver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author : manoj chavan Parent class for all test classes contains basic
 * methods and test case configuration
 */
@Slf4j
public class Setup {

    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;

    @Autowired
    private PropertySourceResolver propertySourceResolver;
    @Autowired
    private ScenarioSession scenarioSession;

    private static String platformName;
    private static String browserName;
    private static String browserVersion;
    public static String username;
    public static String password;
    public static WebDriver webdriver;
    public static String baseUrl;
    public static String dataSource;
    public static String dataPath;
    public static AppiumDriver mobileDriver;
    private static String APPIUM_WEB_DRIVER_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    boolean webdriverManagerFlag;
    public static String bankUrl;
    public static String securityUrl;
    boolean isParallelCrossbrowser;
    public DriverFactory driverFactory;

    public Setup() {
    	this.driverFactory =new DriverFactory();
    }

    //TBD - From Hooks.java - fill scenarioSession object
    @Before
    public void manageScenarioSessionData(Scenario scenario) {
        log.info("**********************************************************************");
        log.info(
                String.format(
                        "Starting new scenario (%s) and cleaning up the scenario session.",
                        scenario.getName()));

        if (!scenario.getName().equals(scenarioSession.getScenarioName())) {
            scenarioSession.setScenarioName(scenario.getName());
        }
    }

    @Before("@web")
    public void setUp() throws Exception {
        platformName = propertySourceResolver.getPlatformName();
        browserName = propertySourceResolver.getBrowserName();
        browserVersion = propertySourceResolver.getBrowserVersion();
        username = JasyptEncryptor.decrypt(propertySourceResolver.getUserId());
        password = JasyptEncryptor.decrypt(propertySourceResolver.getPassword());
        dataSource = propertySourceResolver.getDataSource();
        dataPath = propertySourceResolver.getDataPath();
        bankUrl=propertySourceResolver.getAppBankUri();
        securityUrl = propertySourceResolver.getWebAppSecurityUri();
        isParallelCrossbrowser=propertySourceResolver.isParallelCrossbrowser();

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps/TamilTest.app");
        
      //boolean check for parallel crossbrowser run using testng- browser parameter 
        if(isParallelCrossbrowser) {
        browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        log.info("browser invoked:"+browserName);
        }
        if(browserName.equalsIgnoreCase("chrome")) {
            //System.setProperty("webdriver.chrome.driver", propertySourceResolver.getChromeDriverPath());
        	//WebDriverManager.chromedriver().driverVersion(browserVersion).setup();
            webdriver=driverFactory.createInstance(browserName);
            System.out.println("Thread ID : "+Thread.currentThread().getId());
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            //System.setProperty("webdriver.gecko.driver", propertySourceResolver.getGeckoDriverPath());
            //WebDriverManager.firefoxdriver().browserVersion(browserVersion).setup();
            webdriver=driverFactory.createInstance(browserName);
            System.out.println("Thread ID : "+Thread.currentThread().getId());
        }
        else if(browserName.equalsIgnoreCase("ie")){
//            System.setProperty("webdriver.ie.driver", propertySourceResolver.getIeDriverPath());
            WebDriverManager.iedriver().setup();
            webdriver=driverFactory.createInstance(browserName);
            System.out.println("Thread ID : "+Thread.currentThread().getId());
        }
        else if(browserName.equalsIgnoreCase("edge")){
//            System.setProperty("webdriver.edge.driver", propertySourceResolver.getEdgeDriverPath());
//            WebDriverManager.edgedriver().browserVersion(browserVersion).setup();
            webdriver=driverFactory.createInstance(browserName);
            System.out.println("Thread ID : "+Thread.currentThread().getId());
      }

        DesiredCapabilities capabilities = new DesiredCapabilities("","", Platform.ANY);
        this.APPIUM_WEB_DRIVER_SERVER_URL = propertySourceResolver.getPerfectoUrl();
        this.baseUrl = propertySourceResolver.getAppBaseUrl();

        if (platformName.equalsIgnoreCase("android")){
            File app = new File(appDir, propertySourceResolver.getAndroidApkFile());
            capabilities.setCapability("platformName",platformName);
            capabilities.setCapability("deviceName",propertySourceResolver.getAndroidDeviceName());
            capabilities.setCapability("platformVersion", propertySourceResolver.getAndroidPlatformVersion());
            capabilities.setCapability("browserName", browserName);
            capabilities.setCapability("securityToken", propertySourceResolver.getPerfectoToken());
//            capabilities.setCapability("app", app.getAbsolutePath());
			androidDriver = new AndroidDriver(new URL(APPIUM_WEB_DRIVER_SERVER_URL), capabilities);
			androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			webdriver = androidDriver;
		} else if (platformName.equalsIgnoreCase("ios")) {
            File app = new File(appDir, "test.app");
            // Configure the desired capabilities
            capabilities.setCapability("deviceName", propertySourceResolver.getIosDeviceName());
            capabilities.setCapability("platformVersion", propertySourceResolver.getIosPlatformVersion());
            capabilities.setCapability("platformName", platformName);
            capabilities.setCapability("browserName", browserName);
            capabilities.setCapability("securityToken", propertySourceResolver.getPerfectoToken());
            //capabilities.setCapability("app", propertySourceResolver.getIosAppFile());
            capabilities.setCapability("launchTimeout", 9000000);

            iosDriver = new IOSDriver(new URL(APPIUM_WEB_DRIVER_SERVER_URL), capabilities);
            iosDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            webdriver = iosDriver;
        } 
//		else {
//            if (browserName.equalsIgnoreCase("chrome")) {
//
//                //if (webdriverManagerFlag = false) {
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("start-maximized"); // open Browser in maximized mode
//                options.addArguments("disable-infobars"); // disabling infobars
//                options.addArguments("--disable-extensions"); // disabling extensions
//                options.addArguments("--disable-gpu"); // applicable to windows os only
//                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//                options.addArguments("--no-sandbox"); // Bypass OS security model
//                webdriver = new ChromeDriver(options);
//				//	} else {
//				//		webdriver = chromeDriverSetup();
//
//			}
//		//}
//			 else if (browserName.equalsIgnoreCase("firefox")) {
//				FirefoxOptions options = new FirefoxOptions();
//				options.addArguments("start-maximized"); // open Browser in maximized mode
//				options.addArguments("--disable-extensions"); // disabling extensions
//				options.addArguments("--disable-gpu"); // applicable to windows os only
//				options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//				options.addArguments("--no-sandbox"); // Bypass OS security model
//
//                webdriver = new FirefoxDriver(options);
//            } else if (browserName.equalsIgnoreCase("ie")) {
//                InternetExplorerOptions options = new InternetExplorerOptions();
//                // options.destructivelyEnsureCleanSession();
//                options.ignoreZoomSettings();
//
//                webdriver = new InternetExplorerDriver(options);
//            }  else if (browserName.equalsIgnoreCase("edge")) {
//                webdriver = new EdgeDriver();
//            }
//
//            webdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//            webdriver.manage().deleteAllCookies();
//            webdriver.manage().window().maximize();
//        }
    }


    @Before("@native")
    public void setUpNative() throws Exception {
        platformName = propertySourceResolver.getPlatformName();
        DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");

        switch (propertySourceResolver.getAppiumExecType()){

            default:
            case "local":

                    AppiumServerUtil.startAppiumServer();
                    Thread.sleep(1000);
                APPIUM_WEB_DRIVER_SERVER_URL= AppiumServerUtil.getAppiumServerAddress();
                break;
            case "perfecto" :
                APPIUM_WEB_DRIVER_SERVER_URL = propertySourceResolver.getPerfectoUrl();
                capabilities.setCapability("securityToken", propertySourceResolver.getPerfectoToken());
                capabilities.setCapability("autoLaunch", true);   capabilities.setCapability("autoInstrument", true);  capabilities.setCapability("takesScreenshot", false);
                break;
            case "browserstack" :
                APPIUM_WEB_DRIVER_SERVER_URL = "https://" +
                        propertySourceResolver.getBrowserstackUsername() + ":" +
                        propertySourceResolver.getBrowserstackToken() + "@"+propertySourceResolver.getBrowserstackURL();
                capabilities.setCapability("autoLaunch", true);     // Whether to install and launch the app automatically.
                capabilities.setCapability("autoInstrument", true); // To work with hybrid applications, install the iOS/Android application as instrumented.
                capabilities.setCapability("takesScreenshot", false);
                break;

        }

        if (platformName.equalsIgnoreCase("android")) {
            File app = new File(appDir, propertySourceResolver.getAndroidApkFile());
            capabilities.setCapability("platformName", platformName);
            capabilities.setCapability("deviceName", propertySourceResolver.getAndroidDeviceName());
            capabilities.setCapability("platformVersion", propertySourceResolver.getAndroidPlatformVersion());
            capabilities.setCapability("app", app.getAbsolutePath());
            androidDriver = new AndroidDriver(new URL(APPIUM_WEB_DRIVER_SERVER_URL), capabilities);
            androidDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            mobileDriver = androidDriver;
        } else if (platformName.equalsIgnoreCase("ios")) {
            File app = new File(appDir, propertySourceResolver.getIosAppFile());
            // Configure the desired capabilities
            capabilities.setCapability("deviceName", propertySourceResolver.getIosDeviceName());
            capabilities.setCapability("platformVersion", propertySourceResolver.getIosPlatformVersion());
            capabilities.setCapability("platformName", platformName);
            capabilities.setCapability("app", app.getAbsolutePath());
            capabilities.setCapability("useNewWDA", true);
            capabilities.setCapability("launchTimeout", 20000);
            capabilities.setCapability("newCommandTimeout", 3600);
            iosDriver = new IOSDriver(new URL(APPIUM_WEB_DRIVER_SERVER_URL), capabilities);
            iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            mobileDriver = iosDriver;
        }

    }
    // WebDriverManager implementation
//	public WebDriver chromeDriverSetup() {
//
//		Generic generic = new Generic();
//		String proxyDetailsWFG = "";
//		String splitChromeVersion = generic.getChromeVersion();
//		String driverVersion = generic.getDriverVersion(splitChromeVersion);
//		WebDriverManager.chromedriver().version(driverVersion).proxy(proxyDetailsWFG).setup();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("start-maximized"); // open Browser in maximized mode
//		options.addArguments("disable-infobars"); // disabling infobars
//		options.addArguments("--disable-extensions"); // disabling extensions
//		options.addArguments("--disable-gpu"); // applicable to windows os only
//		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//		options.addArguments("--no-sandbox"); // Bypass OS security model
//		return new ChromeDriver(options);
//
//	}
}
