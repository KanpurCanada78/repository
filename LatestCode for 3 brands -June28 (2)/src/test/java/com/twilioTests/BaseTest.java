package com.twilioTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	private void BaseClass() {
		PageFactory.initElements(driver, this);
	}

	protected WebDriver driver;
	
	public static Properties properties;
	
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileInputStream("config.properties"));
	}
	
	@BeforeTest
	public void beforeTest() throws Exception
	{
		WebDriverManager.chromedriver().driverVersion(properties.getProperty("ChromeDriverSetupVersion")).setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("In Base class");

	}

    public WebDriver getDriver() {
        return driver;
    }

  
}