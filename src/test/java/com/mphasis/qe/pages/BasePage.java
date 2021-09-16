package com.mphasis.qe.pages;

import com.mphasis.qe.utils.Setup;
import com.mphasis.qe.utils.Wait;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Slf4j
@Component
public class BasePage{
    public WebDriver driver;
    public AppiumDriver mobileDriver;
    public Wait wait;
    public String baseUrl;
    public String bankUrl;
    public String securityUrl;

    public BasePage()  {
        this.driver = new Setup().webdriver;
        this.mobileDriver = new Setup().mobileDriver;
        this.baseUrl = new Setup().baseUrl;
        this.wait = new Wait(this.driver);
        this.bankUrl=new Setup().bankUrl;
        this.securityUrl = new Setup().securityUrl;
    }
}
