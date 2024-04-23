package com.utilites;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	FileReader fileReader;
	Properties properties;
	public WebDriver driver;
	String userdir;
	
	@BeforeClass
	public void getBrowserName() {
		userdir=System.getProperty("user.dir");
		SetupBrowser setupBrowser=new SetupBrowser();
		String browserName=setupBrowser.getName();
		if (browserName.equalsIgnoreCase("CHROME")) {
			chromeBrowserSetup();
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	public void chromeBrowserSetup() {
		System.setProperty("webdriver.chrome.driver", userdir+"/Drivers/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--incognito");
        driver=new ChromeDriver(options);
        
	}
	

}
