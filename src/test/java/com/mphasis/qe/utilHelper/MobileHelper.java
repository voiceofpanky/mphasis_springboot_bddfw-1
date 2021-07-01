package com.mphasis.qe.utilHelper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kanhaiya Prasad
 *
 */
public class MobileHelper {
    public static WebElement scroll(WebDriver driver, String text) {
        String element = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"" + text + "\"));";
        return ((AndroidDriver) driver).findElementByAndroidUIAutomator(element);
    }

    public static void longPress(WebDriver driver, MobileElement element) {
        TouchAction touchAction = new TouchAction((AndroidDriver) driver);
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        touchAction.longPress(PointOption.point(x, y)).perform();

    }

    public static void swipeToLeft(WebDriver driver, WebElement element, String direction,String textToSwap) {
        System.out.println("Swapping " + direction);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> args = new HashMap<>();
        args.put("text", textToSwap);
        args.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: swipe", args);
    }
}
