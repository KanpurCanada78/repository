package com.helper;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	static WebDriver dri;

	public static void WaitTillElementVisible(WebDriver driver, WebElement element) throws IOException, Exception { 
		Thread.sleep(1000);
		new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(element));
		Thread.sleep(1000);
	}
	
	public static void WaitTillElementListVisible(WebDriver driver, List<WebElement> element) throws IOException, Exception {
		Thread.sleep(1000);
		new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOfAllElements(element));
		Thread.sleep(1000);
	}
	
	public static void WaitTillElementVisibleCustomWait(WebDriver driver, WebElement element, long time) throws IOException, Exception { 
		new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time));
	}
	
	public static void waitUntilPresenseOfElementByLocator(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));		
	}
	
	public static void WaitTillElementListInVisible(WebDriver driver, List<WebElement> element) throws IOException, Exception { 
		Thread.sleep(1000);
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(element));
		Thread.sleep(1000);
	}
	

}
