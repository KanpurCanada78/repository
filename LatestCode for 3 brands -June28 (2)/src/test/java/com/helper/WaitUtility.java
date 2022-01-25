package com.helper;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	static WebDriver dri;

	public static void WaitTillElementVisible(WebDriver driver, WebElement element) throws IOException, Exception {
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
		Thread.sleep(1000);
	}
	
	public static void WaitTillElementListVisible(WebDriver driver, List<WebElement> element) throws IOException, Exception {
			new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(element));
			Thread.sleep(1000);
	}

}
