package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ui.pages.BasePage;

public class OpenCustomerHistory extends BasePage {

	protected OpenCustomerHistory(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//button[contains(@class,'Twilio-IconButton Twilio-Main')]")
	WebElement twilioIconDropDownElement;
	
	@FindBy(xpath="//div[contains(@class,'Twilio-SideNav-default')]/button")
	List <WebElement> twilioIconDropDownList;
	
	
	
	
}
