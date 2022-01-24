package com.helper;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SharedMethods {

	
	public static void clickElement(WebDriver driver, WebElement element) throws Exception {
		WaitUtility.WaitTillElementVisible(driver, element);
		element.click();
    }
	
	public static void selectElementFromDropdown(WebDriver driver, List<WebElement> options, WebElement parentElement, String value) throws Exception {
		WaitUtility.WaitTillElementVisible(driver, parentElement);
		parentElement.click();
		selectElementFromList(driver, options, value);
    }

	public static void selectElementFromList(WebDriver driver, List<WebElement> options, String value) throws Exception {
		for(WebElement val : options) {
			if(val.getText().contains(value)) {
				clickElement(driver, val);
			}
			else{
				Assert.fail("None of the element was found");
			}
		}		
	}
	
	public static void clearAndEnterText(WebDriver driver, WebElement element, String value) throws Exception {
		WaitUtility.WaitTillElementVisible(driver, element);
		element.clear();
		element.sendKeys(value);	
    }
	
	public static WebElement getElementfromListUsingInnerText(List<WebElement> list, String innerText)
	{
		for(WebElement element :list) {
			if(element.getText().contains(innerText)) {
				return element;
			}
		}
		return null;		
	}
	
	public static WebElement GetElementFromList(List<WebElement> options, String value) throws Exception {
		for(WebElement val : options) {
			if(val.getText().contains(value)) {
				return val;
			}
			else{
				Assert.fail("None of the element was found");
			}
		}
		return null;		
	}
	
	
	
}
