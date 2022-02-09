package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.constant.Constants;
import com.helper.SharedMethods;
import com.helper.WaitUtility;

public class ResponseLibrary extends BasePage {
	public ResponseLibrary(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	WebElement responseSubTypeElement;

	@FindBy(xpath = "//div[contains(@class,'Twilio-CRMContainer')]/descendant::ul/li//h6")
	List<WebElement> responseTypeList;

	@FindBy(xpath = "//div[contains(@class,'Twilio-CRMContainer')]/descendant::ul/li")
	List<WebElement> responseTypeSubList;

	@FindBy(xpath = "//button[text()='Browse']")
	WebElement browseLabel;

	@FindBy(xpath = "//div[contains(@class,'Twilio Twilio-MessageInput')]/descendant::textarea")
	WebElement textAreaMsg;

	@FindBy(xpath = "//div[contains(@class,'Twilio Twilio-MessageInput')]/descendant::p")
	WebElement brandLabelInConversationBody;
	
	@FindBy(xpath="//p[text()='RESPONSE LIBRARY']")
	WebElement clickResponseLib;

	public void clickBrowse() throws Exception {
		SharedMethods.clickElement(driver, browseLabel);
	}

	public void SelectResponseType(String responseType) throws Exception {
		clickBrowse();
		SharedMethods.selectElementFromList(driver, responseTypeList, responseType);
	}

	private WebElement GetResponseSubType(String responseSubType) throws Exception {
		return SharedMethods.GetElementFromList(driver, responseTypeSubList, responseSubType);
	}

	public void ClickSubTypeDetailedView(String responseSubType) throws Exception {
		GetResponseSubType(responseSubType).click();
	}

	public void AddSubTypeMsg(String responseSubType) throws Exception {
		List<WebElement> subList =driver.findElements(By.xpath("//div[contains(@class,'Twilio-CRMContainer')]/descendant::ul/li/ancestor::ul//button"));
		WaitUtility.WaitTillElementListVisible(driver, subList);
		subList.get(0).click();
	}

	public boolean CheckSubTypeMsgAddedInConversationBody() {
		if (textAreaMsg.getText() != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean CheckBrandLabelInBody() {
		if (brandLabelInConversationBody.getText() == Constants.brandName) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clickResponseLibrary() throws Exception {
		SharedMethods.clickElement(driver, clickResponseLib);
	}

}
