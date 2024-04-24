package com.makemytrip;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.utilites.BaseClass;

public class FlightBooking extends BaseClass{
	
	@Test(priority=1)
	public void urlNavigation() {
		driver.get("https://www.makemytrip.com/");
		String expected = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
		String actual=driver.getTitle();
		System.out.println(actual);
		Assert.assertTrue(expected.equals(actual));
		System.out.println("testPassed");
	}
	
	@Test(priority=2)
	public void removeCookies() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		WebElement e1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='webklipper-publisher-widget-container-notification-container']//a[@id='webklipper-publisher-widget-container-notification-close-div']")));
//		e1.click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@class='close']")).click();
		
	}

}
