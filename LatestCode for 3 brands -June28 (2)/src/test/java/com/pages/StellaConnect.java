package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.helper.SharedMethods;
import com.helper.WaitUtility;

public class StellaConnect extends BasePage {
	public StellaConnect(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	@FindBy(xpath = "//input[@name='name']")
	WebElement name;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//div[contains(@class, 'Twilio-CRMContainer-default')]//p")
	List<WebElement> dispositionReasonList;
	
	@FindBy(xpath="//p[text()='STELLA CONNECT']")
	WebElement clickStellaConnect;
	
	@FindBy(xpath="//div[contains(@class,'Twilio-MessageBubble-Body')]/span")
	WebElement customerMessageElement;
	
	public void ClickStellaConnect() throws Exception{
		SharedMethods.clickElement(driver,clickStellaConnect);
	}
	
	public void selectDispositionReason(String reasonType, String reasonSubType) throws Exception{		
		SharedMethods.selectElementFromList(driver, dispositionReasonList, reasonType);
		SharedMethods.selectElementFromList(driver, dispositionReasonList, reasonSubType);		
	}

	public boolean isCustomerMessagePresent(String customerMessage) throws Exception{
		System.out.println("Does method contains cust text: "+customerMessageElement.getText().contains(customerMessage));
		return customerMessageElement.getText().contains(customerMessage);
	}
	
}
