package com.twilioTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	protected WebDriver driver;
	
	public static Properties properties;
	
	protected static Logger log = LogManager.getLogger();
	
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileInputStream("config.properties"));
	}
	
	@BeforeTest
	public void beforeTest() throws Exception{	
		getDriver();
		driver.manage().window().maximize();
	}

    @SuppressWarnings("deprecation")
	public WebDriver getDriver() {    	
    	switch(properties.getProperty("driverType")) {
    	case "Chrome":
    	WebDriverManager.chromedriver().driverVersion(properties.getProperty("ChromeDriverSetupVersion")).setup();
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--disable-notifications");
    	//options.addArguments("");
		driver = new ChromeDriver(options);	
    	break;    	
    	case "Firefox":
        	WebDriverManager.firefoxdriver().driverVersion(properties.getProperty("FirefoxDriverSetupVersion")).setup();
    		driver = new FirefoxDriver();    	
        	break;    	
    	}    	
    	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        return driver;
    }
    
    @AfterTest
	public void afterTest() throws Exception	{	
		//driver.quit();
	}

  
}