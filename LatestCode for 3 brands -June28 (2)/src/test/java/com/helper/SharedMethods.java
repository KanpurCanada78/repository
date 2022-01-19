package com.helper;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SharedMethods {

	
	public static void clickElement(WebElement element) throws Exception {
		WaitUtility.WaitTillElementVisible(element);
		element.click();
    }
	
	public static void selectElementFromDropdown(List<WebElement> options, WebElement parentElement, String value) throws Exception {
		WaitUtility.WaitTillElementVisible(parentElement);
		parentElement.click();
		selectElementFromList(options, value);
    }

	public static void selectElementFromList(List<WebElement> options, String value) throws Exception {
		for(WebElement val : options) {
			if(val.getText().contains(value)) {
				clickElement(val);
			}
			else{
				Assert.fail("None of the element was found");
			}
		}		
	}
	
	public static void clearAndEnterText(WebElement element, String value) throws Exception {
		WaitUtility.WaitTillElementVisible(element);
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
