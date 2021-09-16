package com.mphasis.qe.utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Vikas Swami
 *
 */
@Slf4j
public class WindowHelper {

	private WebDriver driver;

	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void switchToParentWindow() {
		log.info("switching to parent window...");
		driver.switchTo().defaultContent();
	}

	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				log.info("switched to : " + index + " window");
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}
	}

	public void closeAllTabsAndSwitchToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String mainwindow = driver.getWindowHandle();

		for (String window : windows) {
			if (!window.equalsIgnoreCase(mainwindow)) {
				driver.close();
			}
		}
		log.info("switched to main window");
		driver.switchTo().window(mainwindow);
	}

	public void navigateBack() {
		log.info("navigating back");
		driver.navigate().back();
	}

	public void navigateForward() {
		log.info("navigating forward");
		driver.navigate().forward();
	}
}
