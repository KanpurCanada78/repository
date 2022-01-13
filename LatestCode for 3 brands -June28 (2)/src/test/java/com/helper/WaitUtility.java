package com.helper;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twilioTests.BaseTest;

public class WaitUtility extends BaseTest{
	static WebDriver dri;
	
	public WaitUtility() {
		dri=driver;
	}	
	
	public static void WaitTillElementVisible(WebElement element) throws IOException {
		WebDriverWait wait = new WebDriverWait(dri, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element)) ;
    }

}
