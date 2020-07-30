package com.mphasis.qe.pageobjects;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import com.mphasis.qe.utils.Setup;
import com.mphasis.qe.utils.Wait;

import lombok.extern.slf4j.Slf4j;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Slf4j
@Component
public class BasePage{
    public WebDriver driver;
    public Wait wait;
    public String baseUrl;

    public BasePage()  {
        this.driver = new Setup().webdriver;
        this.baseUrl = new Setup().baseUrl;
        this.wait = new Wait(this.driver);
    }
}
