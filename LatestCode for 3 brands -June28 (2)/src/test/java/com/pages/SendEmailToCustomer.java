package com.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.helper.SharedMethods;
import com.helper.WaitUtility;
import com.ui.pages.BasePage;

public class SendEmailToCustomer extends BasePage{
	
	public SendEmailToCustomer(WebDriver driver) {
		super(driver);
	}

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
	
	@FindBy(css = "ul[class*='Twilio-Menu Twilio-UserControls-AccountMenu'] li button")
	List<WebElement> dropDownAgentStatusList;
	
	@FindBy(css = "div[class*='Twilio-Icon Twilio-Icon-AgentBold']")
	WebElement linkAgentDesktop;

	private void selectBrand() throws Exception {
		SharedMethods.selectElementFromDropdown(driver, dropDownBrandParentList, "Bergdorf Goodman");
	}
	
	private void enterSubject() throws Exception {
		SharedMethods.clearAndEnterText(driver, subjectTextBox, "Test Subject Automation");
	}
	
	private void enterEmail() throws Exception {
		SharedMethods.clearAndEnterText(driver, customerEmailTextBox, "Shailendra_Singh@neimanmarcus.com");
	}
	
	private void sendEmail() throws Exception {
		SharedMethods.clickElement(driver, composeEmailButton);
	}
	
	private boolean checkAgentAvailable() throws Exception {
		WaitUtility.WaitTillElementVisible(driver, agentCurrentStatus);
		return agentCurrentStatus.getText().contains("Available");
	}
	
	public void makeAgentAvailable() throws Exception {
		if(!checkAgentAvailable()) {
			SharedMethods.clickElement(driver, agentCurrentStatus);
			SharedMethods.selectElementFromDropdown(driver, dropDownAgentStatusList, "Available");
		}	
	}
	
	public void selectAgentDesktop(String url) throws Exception {
		//SharedMethods.clickElement(driver, linkAgentDesktop);
		driver.get(url);
	}
	
	
	
}
