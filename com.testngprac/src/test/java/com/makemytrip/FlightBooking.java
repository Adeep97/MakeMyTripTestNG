package com.makemytrip;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.utilites.BaseClass;

public class FlightBooking extends BaseClass{
	WebDriverWait wait;
	
	//Locators
	By noKiddingSalePopup=By.id("webklipper-publisher-widget-container-notification-frame");
	By noKiddingSaleCloseBtn = By.xpath("//div[@id='webklipper-publisher-widget-container-notification-container']//a[@id='webklipper-publisher-widget-container-notification-close-div']");
	By loginCloseBtn= By.xpath("//div[@class='imageSlideContainer']//span[@class='commonModal__close']");
	By flightTab= By.xpath("//ul[@class='makeFlex font12 headerIconsGap']//span[text()='Flights']//ancestor::li[@class='menu_Flights']");
	By oneWay= By.xpath("//div[contains(@class, 'makeFlex')]//li[text()='One Way']");
	
	
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
	public void removePopups(){
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement nokiddingSale_popup=wait.until(ExpectedConditions.visibilityOfElementLocated(noKiddingSalePopup));
		driver.switchTo().frame(nokiddingSale_popup);
		WebElement noKidding_closebtn=wait.until(ExpectedConditions.visibilityOfElementLocated(noKiddingSaleCloseBtn));
		noKidding_closebtn.click();
		driver.switchTo().defaultContent();
		WebElement login_closebtn=wait.until(ExpectedConditions.visibilityOfElementLocated(loginCloseBtn));
		login_closebtn.click();
		
	}
	
	@Test(priority=3)
	public void flightsTab() {
		WebElement flight_tab=wait.until(ExpectedConditions.visibilityOfElementLocated(flightTab));
		flight_tab.click();
	}
	
	@Test(priority=4)
	public void selectTripType() {
		WebElement one_way=wait.until(ExpectedConditions.visibilityOfElementLocated(oneWay));
		one_way.click();
	}

}
