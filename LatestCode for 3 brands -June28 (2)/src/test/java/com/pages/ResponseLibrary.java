package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.helper.SharedMethods;

public class ResponseLibrary 
{

	@FindBy(xpath = "(//div[@class='Twilio-Splitter-Pane css-189yxq9'])[4]//ul/li")
	List<WebElement> browseList;
	
	@FindBy(xpath = "//button[text()='Browse']")
	WebElement browseBtn;
	
	private void clickBrowse() throws Exception
	{
		SharedMethods.clickElement(browseBtn);
	}
	private void selectResponseInBrowse() throws Exception
	{
		SharedMethods.selectElementFromDropdown(browseList, browseBtn, "Borderfree");
	}
}
