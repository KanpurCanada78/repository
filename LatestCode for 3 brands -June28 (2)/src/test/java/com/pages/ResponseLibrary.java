package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.constant.Constants;
import com.helper.SharedMethods;

public class ResponseLibrary 
{
	WebElement responseSubTypeElement;
	
	@FindBy(xpath = "//div[contains(@class,'Twilio-CRMContainer')]/descendant::ul/li")
	List<WebElement> responseTypeList;
	
	@FindBy(xpath = "//div[contains(@class,'Twilio-CRMContainer')]/descendant::ul/li")
	List<WebElement> responseTypeSubList;
	
	@FindBy(xpath = "//button[text()='Browse']")
	WebElement browseLabel;
	
	private void clickBrowse() throws Exception
	{
		SharedMethods.clickElement(browseLabel);
	}
	
	private void SelectResponseType(String responseType) throws Exception
	{
		clickBrowse();
		SharedMethods.selectElementFromList(responseTypeList, responseType);
	}
	
	private WebElement GetResponseSubType(String responseSubType) throws Exception
	{
		
		 responseSubTypeElement= SharedMethods.GetElementFromList(responseTypeSubList, responseSubType);
		 return responseSubTypeElement;
		
	}
	
	private void ClickSubTypeDetailedView(String responseSubType) throws Exception {
		
		GetResponseSubType(responseSubType).findElements(By.xpath("/div/div")).get(2).click();
		
	}
	
	private void AddSubTypeMsg(String responseSubType) throws Exception {
		
		GetResponseSubType(responseSubType).findElements(By.xpath("/div/div")).get(0).click();
		
	}
	
	
}
