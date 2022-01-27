package com.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EmailBasePage {	
	public static Properties properties;
	protected WebDriver baseEmailDriver;
	
    protected EmailBasePage () throws FileNotFoundException, IOException{
    	properties = new Properties();
    	properties.load(new FileInputStream("config.properties"));
    	WebDriverManager.chromedriver().driverVersion(properties.getProperty("ChromeDriverSetupVersion")).setup();
    	baseEmailDriver = new ChromeDriver();
        PageFactory.initElements(baseEmailDriver,this);
    }
}
