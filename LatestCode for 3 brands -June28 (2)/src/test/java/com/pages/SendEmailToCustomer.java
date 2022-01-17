package com.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.helper.SharedMethods;
import com.helper.WaitUtility;
import com.twilioTests.BaseTest;

public class SendEmailToCustomer extends BaseTest{
	
	@FindBy(xpath = "//h5[text()='Send Email To Customer']")
	WebElement sendEmailTitle;
	
	@FindBy(xpath = "//div[text()='None']")
	WebElement dropDownBrand;
	
	@FindBy(xpath = "//ul[@role='listbox']")
	WebElement dropDownBrandParentElement;
	
	@FindBy(xpath = "//ul[@role='listbox']/li")
	List<WebElement> dropDownBrandParentList;
	
	@FindBy(xpath = "//input[@value='Enter Subject']")
	WebElement subjectTextBox;
	
	@FindBy(xpath = "//input[@value='Enter a Valid Customer Email']")
	WebElement customerEmailTextBox;
	
	@FindBy(xpath = "//button[text()='Compose Email']")
	WebElement composeEmailButton;
	
	@FindBy(css = "div[class*='Twilio-UserCard-InfoContainer'] span")
	WebElement agentCurrentStatus;
	
	@FindBy(xpath = "ul[class*='Twilio-Menu Twilio-UserControls-AccountMenu'] li button")
	List<WebElement> dropDownAgentStatusList;

	private void selectBrand() throws Exception {
		//SharedMethods.clickElement(dropDownBrandParentElement);
		SharedMethods.selectElementFromDropdown(dropDownBrandParentList, dropDownBrandParentElement, "Bergdorf Goodman");
	}
	
	private void enterSubject() throws Exception {
		SharedMethods.clearAndEnterText(subjectTextBox, "Test Subject Automation");
	}
	
	private void enterEmail() throws Exception {
		SharedMethods.clearAndEnterText(customerEmailTextBox, "Shailendra_Singh@neimanmarcus.com");
	}
	
	private void sendEmail() throws Exception {
		SharedMethods.clickElement(composeEmailButton);
	}
	
	private boolean checkAgentAvailable() throws Exception {
		WaitUtility.WaitTillElementVisible(agentCurrentStatus);
		return agentCurrentStatus.getText().contains("Available");
	}
	
	private void makeAgentAvailable() throws Exception {
		if(!checkAgentAvailable()) {
			agentCurrentStatus.click();
			SharedMethods.selectElementFromDropdown(dropDownAgentStatusList, dropDownBrandParentElement, "Available");
		}	
	}
	
}
