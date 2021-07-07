package com.mphasis.qe.utilHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    /**
     * Checks whether a given udid belongs to an IOS device
     *
     * @param udid
     * @return true if yhe udid is ios
     */
    public static boolean isIos(String udid) {
        return udid.length() > 24;
    }

    /**
     * Checks whether the driver is Android
     *
     * @param driver
     * @return true if driver is AndroidDriver
     *
     */
    public static boolean isAndroid(WebDriver driver) {
        return driver != null && driver.toString().toLowerCase().contains("android");
    }
    /**
     * Use JS SendKeys
     *
     * @param driver the driver
     * @param textField web element
     *  @param text text to be entered
     *
     */
    public static void sendKeys(WebDriver driver, WebElement textField, String text) {
        int i = (Integer) ((AndroidDriver) driver).executeScript(
                "try{var el = document.arguments[0];el.value = 'arguments[1]';return 0;}catch{return 1;}", textField,
                text);

        if (i == 1)
            System.out.println("Unable to sendKeys");
    }

    /**
     * Use Generic.hideKeyboard() instead
     *
     * @param driver
     */
    public static void iosKey_Go(WebDriver driver) {
        IOSDriver iDriver = (IOSDriver) driver;
        // Clicking on Go Key =
        iDriver.findElementByAccessibilityId("Go").click();
    }


    /**
     * Ignores the alert popup by clicking on the Cancel.
     *
     * @param driver the driver
     */
    public static void ignorePopup(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        try {
            driver.findElement(By.name("Cancel")).click();
        } catch (Exception e) {
        }
    }

    /**
     * Android only
     *
     * @param driver
     * @return true if keyboard is present
     */
    public static boolean isKeyboardDisplayed(WebDriver driver) {
        if (isAndroid(driver)) {
            AndroidDriver adriver = (AndroidDriver) driver;
            return adriver.isKeyboardShown();
        }
//		if(isIos(driver)) // Keyboard abcense results in XCUIElementTypeKeyboard element not found delay
//		{
//			IOSDriver idriver = (IOSDriver) driver;
//			return idriver.isKeyboardShown();
//		}
        return false;
    }

    /**
     * Accepts the alert popup by clicking on the Cancel. Used to handle permissions
     * alert popup
     *
     * @param driver the driver
     */
    public static void allowPopup(WebDriver driver) // D
    {
        try {
            driver.findElement(By.id("permission_allow_button")).click(); // Click Allow Button
        } catch (Exception e) {
        }
    }



    public static void scrollIOS(WebDriver driver, WebElement ele1, WebElement ele2) {
        // scroll to the last item in the list by accessibility id
        /*
         * IOSDriver<IOSElement> idriver = (IOSDriver<IOSElement>) driver; Map<String,
         * Object> args = new HashMap<>(); args.put("direction", "down"); //
         * args.put("name", eleName); idriver.executeScript("mobile: scroll", args);
         */

        TouchActions tc = new TouchActions(driver);
        tc.longPress(ele1).moveToElement(ele2).release().build().perform();

    }

    /**
     *
     *
     * @param driver
     * @param text
     * @deprecated Under development. Auto Clicks on Random elements in IOS
     */
    public static void scrollClick(WebDriver driver, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap scrollObject = new HashMap<>();
        scrollObject.put("predicateString", "value == '" + text + "'"); // or name ==' '
        js.executeScript("mobile: scroll", scrollObject);
    }

    /**
     * Scrolls to the given text, case insensitive.(Under development)
     *
     * @param driver the driver
     * @param text   the text
     * @return the web element
     */
    public static WebElement scroll1(WebDriver driver, String text) {
        String automatorString1 = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textMatches(\""
                + text + "\"));";
        System.out.println("automatorString1 : " + automatorString1);

      return ((AndroidDriver) driver).findElementByAndroidUIAutomator(automatorString1);
    }

    /**
     * Scrolls to the end of the page.
     *
     * @param driver the driver
     */
    public static void scrollToEnd(WebDriver driver) {
        try {
//			String automatorString = "new UiScrollable(new UiSelector()).scrollToEnd(3);";
//			((AndroidDriver) driver).findElementByAndroidUIAutomator(automatorString);
            ((AndroidDriver) driver).findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(9);");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Scrolls to the given text.
     *
     * @param driver the driver
     * @return void
     *
     */
    public static void scrollToText(WebDriver driver, String text) {
        String automatorString = "new UiScrollable(new UiSelector()).scrollTextIntoView(\"" + text + "\")";
        System.out.println(automatorString);
        ((AndroidDriver) driver).findElementByAndroidUIAutomator(automatorString);
        MobileElement e = (MobileElement) ((AndroidDriver) driver).findElementByAndroidUIAutomator(automatorString);

//MobileElement elementToClick = (MobileElement) ((FindsByAndroidUIAutomator<MobileElement>) driver)
//    .findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
//        + ".resourceId(\"com.android.settings:id/content\")).scrollIntoView("
//        + "new UiSelector().text(\"Lock screen and security\"));");
//elementToClick.click();
    }

    // scrolls to the element with the mentioned text and clicks it

    public static void scrollToElement(WebDriver driver, String elementVisibleText) {
        String uiSelector = "new UiSelector().textMatches(\"" + elementVisibleText + "\")";
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector
                + ");";
        ((AndroidDriver) driver).findElementByAndroidUIAutomator(command);
    }
}
