package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StellaConnect {

	
	@FindBy(xpath = "//input[@name='name']")
	WebElement name;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//div[contains(@class,'Twilio-CRMContainer')]/descendant::div[@role='button']")
	List<WebElement> list;
	
	
	
	private boolean CheckName() {
		if (name.getText() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean CheckEmail() {
		if (email.getText() != null) {
			return true;
		} else {
			return false;
		}
	}
	
}
