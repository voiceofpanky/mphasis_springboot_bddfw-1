package com.mphasis.qe.pageobjects;
import com.mphasis.qe.PropertySourceResolver;
import com.mphasis.qe.utils.Setup;
import com.mphasis.qe.utils.Wait;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
