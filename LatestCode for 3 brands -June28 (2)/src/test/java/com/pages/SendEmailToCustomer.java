package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.constant.Constants;
import com.helper.SharedMethods;
import com.helper.WaitUtility;

public class SendEmailToCustomer extends BasePage{

	public SendEmailToCustomer(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button/span[text()='Back to tools']")
	WebElement backToToolBtn;

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

	@FindBy(xpath="//div[contains(text(),'Incoming email request')]/parent::div/following-sibling::div")
	WebElement acceptCustEmail;
	
	@FindBy(xpath="//span[contains(text(),'Incoming chat request')]/ancestor::div[contains(@class, 'Twilio-TaskListBaseItem-Content')]/following-sibling::div")
	WebElement acceptCustChat;

	@FindBy(xpath="//button[contains(@class,'Twilio-Button flex-md' )][text()='Send']")
	WebElement sendResponseToCust;

	@FindBy(xpath="//button[contains(@class,'Twilio-Button flex-md')]/span[text()='COMPLETE']")
	WebElement clickTaskComplete;
	
	@FindBy(xpath="//textarea[contains(@class, 'flex')]")
	List<WebElement> chatResponseTextArea;
	
	@FindBy(xpath="//span[text()='END CHAT']")
	WebElement elementEndChat;
	
	@FindBy(xpath="//button[contains(@class, 'Twilio-IconButton flex-md')]")
	List<WebElement> chatResponseSendButton;

	public void selectBrand() throws Exception {
		SharedMethods.selectElementFromDropdown(driver, dropDownBrandParentList, Constants._brandName);
	}

	public void enterSubject() throws Exception {
		SharedMethods.clearAndEnterText(driver, subjectTextBox, "Test Subject Automation");
	}

	public void enterEmail() throws Exception {
		SharedMethods.clearAndEnterText(driver, customerEmailTextBox, "Shailendra_Singh@neimanmarcus.com");
	}

	public void sendEmail() throws Exception {
		SharedMethods.clickElement(driver, composeEmailButton);
	}

	private boolean checkAgentAvailable() throws Exception {
		WaitUtility.WaitTillElementVisible(driver, agentCurrentStatus);
		return agentCurrentStatus.getText().contains("Unavailable");
	}

	public void makeAgentAvailable() throws Exception {
		if(checkAgentAvailable()) {
			SharedMethods.clickElement(driver, agentCurrentStatus);
			SharedMethods.selectElementFromDropdown(driver, dropDownAgentStatusList, "Available");
		}	
	}

	public void acceptCustEmailRequest() throws Exception {
		SharedMethods.clickElement(driver, acceptCustEmail);
	}
	public void acceptCustChatRequest() throws Exception {
		SharedMethods.clickElement(driver, acceptCustChat);
	}

	public void selectAgentDesktop(String url) throws Exception {
		driver.get(url);
	}
	
	public void clickBackToTools() throws Exception {
		SharedMethods.clickElement(driver, backToToolBtn);
	}

	public void sendResponse() throws Exception {
		SharedMethods.clickElement(driver, sendResponseToCust);
	}

	public void clickCompleteResponse() throws Exception {
		SharedMethods.clickElement(driver, clickTaskComplete);
	}
	
	public void sendChatResponse() throws Exception {
		SharedMethods.clearAndEnterText(driver, chatResponseTextArea.get(2), Constants._agentMessage);
		WaitUtility.WaitTillElementListVisible(driver, chatResponseSendButton);
		SharedMethods.clearAndEnterText(driver, chatResponseSendButton.get(2), Constants._agentMessage);
	}
	
	public void endChat() throws Exception {
		SharedMethods.clickElement(driver, elementEndChat);
	}
	
	

}
