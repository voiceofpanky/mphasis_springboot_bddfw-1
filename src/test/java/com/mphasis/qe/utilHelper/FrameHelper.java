package com.mphasis.qe.utilHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Vikas Swami
 *
 */
@Slf4j
public class FrameHelper {

	private WebDriver driver;

	public FrameHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void switchToFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
		log.info("switched to :" + frameIndex + " frame");
	}

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("switched to :" + frameName + " frame");
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("switched to frame " + element.toString());
	}
}
