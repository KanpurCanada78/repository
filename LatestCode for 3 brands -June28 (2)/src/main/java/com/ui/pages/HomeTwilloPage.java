package com.ui.pages;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.app.utility.GeneralUtility;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class HomeTwilloPage extends BasePage {

	WebDriver driver;

	public HomeTwilloPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[@class='Twilio-Side-Link css-mfu8ej'][1]")
	WebElement Taskicon;

	@FindBy(xpath = "//div[contains(@class, 'Twilio-Dropdown')]")
	WebElement dropdownView;

	@FindBy(xpath = "//div[@class='Twilio-Dropdown css-hb3i4v']")
	WebElement dropdown;

	@FindBy(xpath = "//li[text()='Available']")
	WebElement avilable;

	@FindBy(xpath = "//div[text()='Offline']")
	WebElement offline;

	@FindBy(xpath = "//textarea[@aria-invalid='false']")
	WebElement textemail;

	@Step("Task Checkout in twilio")
	public WebDriver taskchekwithoutbound(WebDriver driver, Properties properties, String customerEmail, String customerFirstLastName, Boolean availableCheck) throws Exception

	{
		if(availableCheck) { 
			//Wait after twilio login is completed
			Thread.sleep(50000);
			driver.findElement(By.xpath("//body")).sendKeys(Keys.F5);
			//Wait for some more time to refresh the body page
			Thread.sleep(2000);
			Taskicon.click();
			//Wait before going to available
		    Thread.sleep(Integer.valueOf(properties.getProperty("wait.twilio.before.available")));
			new Actions(driver).moveToElement(dropdownView).click().build().perform();
			//Wait for all dropdowns to be visible
			Thread.sleep(2500);
			avilable.click();
			//Wait after going to available
			Thread.sleep(Integer.valueOf(properties.getProperty("wait.twilio.after.available")));
		}
		//below xpath will be changed as per inbound customer email
		try {
			Assert.assertEquals(driver.findElement(By.xpath("//*[text()='"+customerEmail+"']")).getText(), customerEmail);

		}
		catch(Exception e) {
			Assert.assertEquals(driver.findElement(By.xpath("//*[text()='"+customerFirstLastName+"']")).getText(), customerFirstLastName);
		}
		
		Allure.addAttachment("Email received to Twilio agent", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		
		try {
			driver.findElement(By.xpath("//*[text()='"+customerEmail+"']//following::div[2]")).click();
		}
		catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='"+customerFirstLastName+"']//following::div[2]")).click();
		}
		//Wait after opening email from customer to take proper screenshot
		Thread.sleep(3000);
		Allure.addAttachment("Email opened by Twilio agent", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		
		return driver;
	}

	@Step("Replying email from Twilio to customer")
	public String emailReplyToCustomer(WebDriver driver, Properties properties) throws InterruptedException {

		Allure.addAttachment("Email opened by Twilio agent", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		String emailtext = "Hi  customer " + GeneralUtility.randonText();
		driver.findElement(By.xpath("//textarea[@aria-invalid='false']")).sendKeys(emailtext);
		//textemail.sendKeys(emailtext);
		driver.findElement(By.xpath("//*[text()='Send']")).click();
		driver.findElement(By.xpath("//*[text()='CLOSE TICKET']")).click();
		driver.findElement(By.xpath("//*[text()='WEBSITE / TECHNICAL']")).click();
		driver.findElement(By.xpath("//*[text()='Unintended address']")).click();
		Allure.addAttachment("Twilio agent reply to customer email", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		driver.findElement(By.xpath("//*[text()='COMPLETE']")).click();
		//Wait after task is completed by Twilio agent
		Thread.sleep(3000);
		setDriverAfterTaskComplete(driver);
		return emailtext;
	}

	public WebDriver getDriverAfterTaskComplete() {
		return driver;
	}

	public void setDriverAfterTaskComplete(WebDriver driverTask) {
		driver = driverTask;
	}

}
