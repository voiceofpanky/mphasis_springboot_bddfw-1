package com.mphasis.qe.pageobjects;
import com.mphasis.qe.PropertySourceResolver;
import com.mphasis.qe.utils.Setup;
import com.mphasis.qe.utils.Wait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/****************************************************************************************
 * @author manoj chavan
 ****************************************************************************************/
@Component
public class BasePage{
    private static final Logger LOGGER = LogManager.getLogger(BasePage.class.getSimpleName());
    public WebDriver driver;
    public Wait wait;
    public String baseUrl;

    public BasePage()  {
        this.driver = new Setup().webdriver;
        this.baseUrl = new Setup().baseUrl;
        this.wait = new Wait(this.driver);
    }
}
