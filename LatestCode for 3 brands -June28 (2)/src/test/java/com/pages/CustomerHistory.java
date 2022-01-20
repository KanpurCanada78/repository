package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.helper.SharedMethods;
//import com.helper.WaitUtility;

public class CustomerHistory {

	@FindBy(xpath = "//button[text()='Reset']")
	WebElement resetBtn;

	@FindBy(xpath = "//button[text()='Filter']")
	WebElement filterBtn;

	@FindBy(xpath = "//button/span[text()='Back to tools']")
	WebElement backToToolBtn;

	@FindBy(xpath = "//div[text()='All']")
	WebElement dropDownChannelElement;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	List<WebElement> dropDownChannelList;

	@FindBy(xpath = "//div[text()='None']")
	WebElement dropDownRangeElement;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	List<WebElement> dropDownRangeList;

	@FindBy(xpath = "(//div[@class='Twilio-Icon Twilio-Icon-Custom  css-y8bnhq'])[4]")
	WebElement eyeDetailedView;

	private void SelectChannel() throws Exception {
		// SharedMethods.clickElement(dropDownChannelElement);
		SharedMethods.selectElementFromDropdown(dropDownChannelList, dropDownChannelElement, "Email");
	}

	private void SelectRange() throws Exception {
		SharedMethods.selectElementFromDropdown(dropDownRangeList, dropDownRangeElement, "");
	}

	private void ClickFilter() throws Exception {
		SharedMethods.clickElement(filterBtn);
	}

	private void ClickReset() throws Exception {
		SharedMethods.clickElement(resetBtn);
	}

	private void viewDetailed() throws Exception {
		SharedMethods.clickElement(eyeDetailedView);
	}

}
