package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.helper.SharedMethods;

public class StellaConnect extends BasePage {
	public StellaConnect(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	@FindBy(xpath = "//input[@name='name']")
	WebElement name;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//div[contains(@class,'Twilio-CRMContainer')]/descendant::div[@role='button']")
	List<WebElement> list;
	
	@FindBy(xpath="//p[text()='STELLA CONNECT']")
	WebElement clickStellaConnect;
	
	public void ClickStellaConnect() throws Exception{
		SharedMethods.clickElement(driver,clickStellaConnect);
	}
	
	public void selectDispositionReason(String reasonType, String reasonSubType) throws Exception{
		
		WebElement reasonTp=driver.findElement(By.xpath("//button/following-sibling::div/div[2]/div/div/div/div/p[text()='"+reasonType+"']"));
		SharedMethods.clickElement(driver, reasonTp);
		//button/span[text()='Back to tools']/parent::button/following-sibling::div/div[2]/div/div/div/div/p[text()='"+reasonType+"']
		WebElement subReasonTp=reasonTp.findElement(By.xpath("/ancestor::div[2]/following-sibling::div/descendant::ul/li/button/div/span/div[text()='"+reasonSubType+"']"));	
		SharedMethods.clickElement(driver, subReasonTp);
		
	}
	
}
