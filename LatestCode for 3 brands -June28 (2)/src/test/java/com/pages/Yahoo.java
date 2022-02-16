package com.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.app.utility.GeneralUtility;
import com.helper.SharedMethods;
import com.helper.WaitUtility;
import io.qameta.allure.Step;

public class Yahoo extends BasePage {
	Properties properties;

	public Yahoo(WebDriver driver)  {
		super(driver);
	}

	@FindBy(xpath = "//*[@data-test-id='displayed-count']")
	WebElement inboxcount;

	@FindBy(xpath = "//*[@data-test-id='checkbox']")
	WebElement echeckbox;

	@FindBy(css = "button[title*=\"Delete\"]")
	WebElement delete;

	@FindBy(xpath = "//*[@id='login-passwd']")
	WebElement ypassword;

	@FindBy(xpath = "//*[@name='username']")
	WebElement yusername;

	@FindBy(xpath = "//*[@name='signin']")
	WebElement btnsignin;

	@FindBy(xpath = "//*[text()='Sign in']")
	WebElement ysginin;

	@FindBy(xpath = "//*[@id='login-signin']")
	WebElement pnext;

	@FindBy(xpath = "//*[@id='ybarMailLink']/span[1]")
	WebElement messagebox;

	@FindBy(css="a#ymail")
	WebElement mailLink;

	@FindBy(xpath = "//a[text()='Compose'][@role='button']")
	WebElement compose;
	//*[@id=\\\"app\\\"]/div[2]/div/div[1]/nav/div/div[1]/a


	@FindBy(xpath = "//*[@id='message-to-field']")
	WebElement toemail;

	@FindBy(xpath = "//*[@id='mail-app-component']/div/div/div[1]/div[3]/div/div/input")
	WebElement subject;

	@FindBy(xpath = "//*[@id='mail-app-component']/div/div/div[2]/div[2]/div/button/span")
	WebElement btnsent;

	@FindBy(xpath = "//*[text()='Spam']")
	WebElement spam;

	@FindBy(xpath = "//*[text()='OK']")
	WebElement sdeleteok;

	public void signInToYahooMailAndSendEmail() throws Exception {	
		properties = new Properties();
		properties.load(new FileInputStream("config.properties"));
		driver.get(properties.getProperty("EmailUrl"));
		driver.manage().window().maximize();
		ySignIn(properties.getProperty("YahooUserEmail"), properties.getProperty("YahooUserEmailPassword"));
		composeEmail("CustomerCare_NMQA@neimanmarcus.com", "TestNMemailtest");
	}

	public void ySignIn(String username, String pwd) throws Exception {
		//SharedMethods.clickElement(driver, ysginin);
		SharedMethods.clearAndEnterText(driver, yusername, username);
		SharedMethods.clickElement(driver, btnsignin);
		SharedMethods.clearAndEnterText(driver, ypassword, pwd);
		SharedMethods.clickElement(driver, pnext);
		SharedMethods.clickElement(driver, messagebox);
		//SharedMethods.clickElement(driver, mailLink);
	}

	@Step("Composing email from customer")
	public void composeEmail(String email, String sub) throws Exception {
		Thread.sleep(2000);
		compose.click();
		toemail.sendKeys(email);
		subject.sendKeys("TestNMemailtest"+GeneralUtility.randonText());
		btnsent.click();
		Thread.sleep(3000);
	}

	@Step("Verify resposne received from twilio to customer")
	public String emailVerification(WebDriver driver2) throws Exception {
		String value = null;
		driver2.findElement(By.xpath("//body")).sendKeys(Keys.F5);
		driver2.findElement(By.xpath("//*[text()='Inbox']")).click();
		Thread.sleep(90000);
		boolean isPresent = driver2.findElements(By.xpath("//*[contains(text(), \"CustomerCare_NMQA@s.neimanmarcus.com\")]")).size() > 0;
		if (isPresent) {
			value = driver2.findElement(By.xpath("//*[contains(text(), \"CustomerCare_NMQA@s.neimanmarcus.com\")]")).getText();
		}

		else {
			driver2.findElement(By.xpath("//*[text()='Spam']")).click();
			Thread.sleep(30000);
			value = driver2.findElement(By.xpath("//*[contains(text(), \"CustomerCare_NMQA@s.neimanmarcus.com\")]")).getText();
		}
		Thread.sleep(4000);

		return value;
	}

	public void deletemail(WebDriver driver) {
		try {
			WaitUtility.WaitTillElementVisible(driver, echeckbox);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if (driver.findElements(By.xpath("//*[@data-test-id='checkbox']")).size() > 0) {
			echeckbox.click();
			delete.click();
		}
	}

	public void spamDelete(WebDriver driver) throws Exception {
		spam.click();
		Thread.sleep(6000);
		if(driver.findElements(By.xpath("//*[@data-test-id='checkbox']")).isEmpty()) {
			WaitUtility.WaitTillElementListVisible(driver, driver.findElements(By.xpath("//*[@data-test-id='checkbox']")));
			//wait.until(ExpectedConditions.visibilityOfAllElements(driver4.findElements(By.xpath("//*[@data-test-id='checkbox']"))));	
			if (driver.findElements(By.xpath("//*[@data-test-id='checkbox']")).size() > 0) {
				echeckbox.click();
				delete.click();
				sdeleteok.click();

			} else {

			}
		}


	}

}
