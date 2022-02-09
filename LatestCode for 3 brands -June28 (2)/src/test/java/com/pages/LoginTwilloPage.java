package com.pages;

import java.util.Properties;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.helper.SharedMethods;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.qameta.allure.Step;

public class LoginTwilloPage extends BasePage {

	public LoginTwilloPage(WebDriver driver) {
		super(driver);		
	}

	@FindBy(xpath="//input[@id='email']")
	WebElement username;


	@FindBy(xpath="//input[@id='password']")
	WebElement password;

	@FindBy(xpath="//button[@role='login-button']")
	WebElement button;

	@FindBy(id="token-input")
	WebElement opt;

	@FindBy(xpath="//*[text()='Verify']")
	WebElement verifyButton;

	@Step("Logging into Twilio account")    
	public  void login(String loginname,String pwd, Properties properties ) throws Exception {
		SharedMethods.clearAndEnterText(driver, username, loginname);
		SharedMethods.clickElement(driver, button);
		SharedMethods.clearAndEnterText(driver, password, pwd);
		SharedMethods.clickElement(driver, button);
		Thread.sleep(20000);
		SharedMethods.clickElement(driver, verifyButton);
	}
	
	public static String getMessage(Properties properties) {
		return getMessages(properties).filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals(properties.getProperty("TwilioFlexOTPNumber"))).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}

	private static Stream<Message> getMessages(Properties properties) {
		ResourceSet<Message> messages = Message.reader().read();
		return StreamSupport.stream(messages.spliterator(), false);
	}
	

}
