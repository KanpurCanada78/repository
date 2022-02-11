package com.twilioTests;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.app.utility.GeneralUtility;
import com.helper.EmailClientType;
import com.helper.EmailCommonSteps;
import com.helper.EmailUtils;
import com.listen.CustomListen;
import com.pages.CustomerHistory;
import com.pages.LoginTwilloPage;
import com.pages.ResponseLibrary;
import com.pages.SendEmailToCustomer;
import com.pages.StellaConnect;
import com.pages.Yahoo;
import com.ui.pages.HomeTwilloPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

//@Listeners({ CustomListen.class })
public class TwilioEmailTest extends BaseTest{
	public static LoginTwilloPage  lp;
	public static HomeTwilloPage hp;
	public static final Logger log = Logger.getLogger(TwilioEmailTest.class);
	public static EmailUtils emailUtils = new EmailUtils();
	public static EmailCommonSteps emailCommonSteps;
	public static String emailAddress;
	public static String emailPassword;
	public static SendEmailToCustomer _sendEmailToCustomerPage;
	public static ResponseLibrary _responseLibrary;
	public static StellaConnect _stellaConnect;

	@Test(priority = 1)
	@Description("Verify whether   customer is able to send an email to twilio flex account")
	public void Inbound_NM_CustomerEmailToTwilio() throws Exception
	{
		Yahoo yah = new Yahoo(driver);
		yah.signInToYahooMailAndSendEmail();
		driver.get(properties.getProperty("TwilioFlexUrl"));
		lp=new com.pages.LoginTwilloPage(driver);
		lp.login(properties.getProperty("TwilioFlexUsername"), properties.getProperty("TwilioFlexPassword"), properties);
		_sendEmailToCustomerPage = new SendEmailToCustomer(driver);
		_sendEmailToCustomerPage.makeAgentAvailable();
		_sendEmailToCustomerPage.selectAgentDesktop(properties.getProperty("TwilioAgentDesktop"));
		_sendEmailToCustomerPage.acceptCustEmailRequest();
		_sendEmailToCustomerPage.makeAgentAvailable();
		_sendEmailToCustomerPage.clickBackToTools();
		_responseLibrary = new ResponseLibrary(driver);
		_responseLibrary.clickResponseLibrary();
		_responseLibrary.selectResponseType("Borderfree");
		_responseLibrary.addSubTypeMsg("BF - Accepted Payment");
		Assert.assertTrue(_responseLibrary.CheckSubTypeMsgAddedInConversationBody());
		_sendEmailToCustomerPage.clickBackToTools();
		_stellaConnect =new StellaConnect(driver);
		_stellaConnect.ClickStellaConnect();
		_stellaConnect.selectDispositionReason("RETURNS","RMA");
		_sendEmailToCustomerPage.sendResponse();
		_sendEmailToCustomerPage.clickCompleteResponse();
	}

	//@Test(dependsOnMethods = {"Inbound_NM_CustomerEmailToTwilio"}, priority = 2)
	@Description("Verify whether twilio flex agent is able to reply email from customer ")
	public void Outbound_NM_twilioReplyToCustomerEmail() throws Exception
	{
		String contentReplyFromTwilio = hp.emailReplyToCustomer(driver, properties);
		//Wait for email reply from twilio to customer
		Thread.sleep(Integer.valueOf(properties.getProperty("wait.email.reply.from.twilio.to.customer")));
		verifyEmailDetails(properties.getProperty("TwilioNMemailReply"), contentReplyFromTwilio);

	}

	//@Test(priority= 3)
	@Description("Verify whether customer is able to send an email to BG twilio flex account")
	public void Inbound_BG_CustomerEmailToTwilio() throws Exception
	{
		emailAddress = properties.getProperty("BGEmailUser");
		emailPassword = properties.getProperty("BGEmailPassword");
		//emailUtils.connectToInbox(emailAddress, emailPassword, EmailClientType.GMAIL);
		emailUtils.connectToInbox(emailAddress, emailPassword, EmailClientType.YAHOO);
		emailUtils.deleteEmails();
		Long currentTime = System.currentTimeMillis();
		emailUtils.sendEmail(emailAddress, emailPassword, properties.getProperty("TwilioBGemail"),
				"Automation Test BG "+currentTime, "Hello, What is the status on my automation test order WN"+currentTime);
		//Wait after email sent from customer to twilio agent
		Thread.sleep(Integer.valueOf(properties.getProperty("wait.email.sent.from.customer.to.twilio")));
		driver = hp.getDriverAfterTaskComplete();
		driver=hp.taskchekwithoutbound(driver, properties,properties.getProperty("BGEmailUser"), properties.getProperty("BGEmailUserFirstNameLastName"), false);    	

	}

	//@Test(dependsOnMethods = {"Inbound_BG_CustomerEmailToTwilio"}, priority = 4)
	@Description("Verify whether twilio flex agent is able to reply email from customer ")
	public void Outbound_BG_twilioReplyToCustomerEmail() throws Exception
	{
		String contentReplyFromTwilio = hp.emailReplyToCustomer(driver, properties);
		//Wait for email reply from twilio to customer
		Thread.sleep(Integer.valueOf(properties.getProperty("wait.email.reply.from.twilio.to.customer")));
		verifyEmailDetails(properties.getProperty("TwilioBGemailReply"), contentReplyFromTwilio);

	}

	//@Test(priority = 5)
	@Description("Verify whether customer is able to send an email to HC twilio flex account")
	public void Inbound_HC_CustomerEmailToTwilio() throws Exception
	{
		emailAddress = properties.getProperty("HCEmailUser");
		emailPassword = properties.getProperty("HCEmailPassword");
		emailUtils.connectToInbox(emailAddress, emailPassword, EmailClientType.GMAIL);
		emailUtils.deleteEmails();
		Long currentTime = System.currentTimeMillis();
		emailUtils.sendEmail(emailAddress, emailPassword, properties.getProperty("TwilioHCemail"),
				"Automation Test HC "+currentTime, "Hello, What is the status on my automation test order WN"+currentTime);
		//Wait after email sent from customer to twilio agent
		Thread.sleep(Integer.valueOf(properties.getProperty("wait.email.sent.from.customer.to.twilio")));
		driver = hp.getDriverAfterTaskComplete();
		driver=hp.taskchekwithoutbound(driver, properties,properties.getProperty("HCEmailUser"), properties.getProperty("HCEmailUserFirstNameLastName"), false);


	}

	//@Test(dependsOnMethods = {"Inbound_HC_CustomerEmailToTwilio"}, priority = 6)
	@Description("Verify whether twilio flex agent is able to reply email from customer ")
	public void Outbound_HC_twilioReplyToCustomerEmail() throws Exception
	{
		String contentReplyFromTwilio = hp.emailReplyToCustomer(driver, properties);
		//Wait for email reply from twilio to customer
		Thread.sleep(Integer.valueOf(properties.getProperty("wait.email.reply.from.twilio.to.customer")));
		verifyEmailDetails(properties.getProperty("TwilioHCemailReply"), contentReplyFromTwilio);
		emailUtils.disconnectInbox();

	}

	/*
	 * @AfterTest public void afterTest() { driver.quit(); }
	 */

	//@Step("Verify Email from twilio agent")
	private void verifyEmailDetails(String EmailAddress, String EmailBody) throws MessagingException, IOException {
		Message message = emailUtils.getRecentEmailMessage();
		Assert.assertEquals(((InternetAddress)message.getFrom()[0]).getAddress(), EmailAddress);
		Assert.assertEquals(message.getContent().toString().trim().contains(EmailBody), true);
	}


}
