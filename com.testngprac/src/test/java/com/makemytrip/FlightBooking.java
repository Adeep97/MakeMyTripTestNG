package com.makemytrip;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.utilites.BaseClass;

@Listeners(com.utilites.ListenersClass.class)
public class FlightBooking extends BaseClass{
	WebDriverWait wait;
	static String formattedDepartureDate;
	static String formattedDepartureDateDD;
	
	//Locators
	By noKiddingSalePopup=By.id("webklipper-publisher-widget-container-notification-frame");
	By noKiddingSaleCloseBtn = By.xpath("//div[@id='webklipper-publisher-widget-container-notification-container']//a[@id='webklipper-publisher-widget-container-notification-close-div']");
	By loginCloseBtn= By.xpath("//div[@class='imageSlideContainer']//span[@class='commonModal__close']");
	By flightTab= By.xpath("//ul[@class='makeFlex font12 headerIconsGap']//span[text()='Flights']//ancestor::li[@class='menu_Flights']");
	By oneWay= By.xpath("//div[contains(@class, 'makeFlex')]//li[text()='One Way']");
	By fromCity =By.xpath("//input[@id='fromCity']/../..");
	By fromCityInput = By.xpath("//input[contains(@class, 'react-autosuggest__input react-autosuggest__')]");
	By bangaloreSuggest = By.xpath("//li[contains(@class, 'react-autosuggest__suggestion')]//span[text()='Bengaluru']");
	By toCity=By.xpath("//div[contains(@class, 'flt_fsw_inputBox searchToCity')]");
	By mumbaiSuggest = By.xpath("//li[contains(@class, 'react-autosuggest__suggestion react-autosuggest__suggestion')]//span[text()='Mumbai']");
	By departureDateField=By.xpath("//span[text()='Departure']/../..");
	By departueDynamicDate=By.xpath("//p[text()='"+formattedDepartureDateDD+"']//ancestor::div[contains(@aria-label,"+"'"+formattedDepartureDate+"'"+")]and contains(@class, 'DayPicker-Day')");
	By ReturnDateField=By.xpath("//span[text()='Return']/../..");
	By studentFare=By.xpath("//div[@class='fareCardItem ']//div[text()='Student']");
	By searchBtn=By.xpath("//a[text()='Search']");
	
	
//	For debugging
	@AfterMethod
	public void waitTime() throws InterruptedException {
		Thread.sleep(3000);
	}
	
	
	@Test(priority=1)
	public void urlNavigation() {
		driver.get("https://www.makemytrip.com/");
		String expected = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
		String actual=driver.getTitle();
		System.out.println(actual);
		Assert.assertTrue(expected.equals(actual));
		System.out.println("testPassed");
	}
	
	@Test(priority=2, dependsOnMethods = {"urlNavigation"})
	public void removePopups(){
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement nokiddingSale_popup=null;
		try {
		nokiddingSale_popup = wait.until(ExpectedConditions.visibilityOfElementLocated(noKiddingSalePopup));
		WebElement noKidding_closebtn = null;
			try {
			    noKidding_closebtn = wait.until(ExpectedConditions.visibilityOfElementLocated(noKiddingSaleCloseBtn));
			    if(noKidding_closebtn.isDisplayed()){	
			    driver.switchTo().frame(nokiddingSale_popup);
			    noKidding_closebtn.click();
			    }
			} catch (TimeoutException e) {
			    System.out.println("Close button not found or not displayed");
			    e.printStackTrace();
			} 
		}catch(TimeoutException e) {
			System.out.println("Frame not found or not displayed");
		    e.printStackTrace();
		}
		finally {
		     driver.switchTo().defaultContent();
		}
		WebElement login_closebtn=wait.until(ExpectedConditions.visibilityOfElementLocated(loginCloseBtn));
		login_closebtn.click();
		
	}
	
	@Test(priority=3, dependsOnMethods = {"removePopups"})
	public void flightsTab() {
		WebElement flight_tab=wait.until(ExpectedConditions.visibilityOfElementLocated(flightTab));
		flight_tab.click();
	}
	
	@Test(priority=4, dependsOnMethods = {"flightsTab"})
	public void selectTripType() {
		WebElement one_way=wait.until(ExpectedConditions.visibilityOfElementLocated(oneWay));
		one_way.click();
	}
	
	@Test(priority=5, dependsOnMethods = {"selectTripType"})
	public void fromCity(){
		WebElement from_city=driver.findElement(fromCity);
		from_city.click();
		try {
		WebElement from_cityInput=wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityInput));
		from_cityInput.sendKeys("Bengaluru");
		}catch(TimeoutException e) {
			e.printStackTrace();
		}
		WebElement bangalore_suggest=null;
		try{
			bangalore_suggest=wait.until(ExpectedConditions.visibilityOfElementLocated(bangaloreSuggest));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		bangalore_suggest.click();
	}
	
	@Test(priority=6, dependsOnMethods= {"fromCity"})
	public void toCity(){
		WebElement to_city=driver.findElement(toCity);
		to_city.click();
		try {
			WebElement from_cityInput=wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityInput));
			from_cityInput.sendKeys("Mumbai");
			}catch(TimeoutException e) {
				e.printStackTrace();
			}
		WebElement mumbai_suggest=null;
		try {
			mumbai_suggest=wait.until(ExpectedConditions.visibilityOfElementLocated(mumbaiSuggest));
			mumbai_suggest.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	
	@Test(priority=7, dependsOnMethods= {"toCity"})
	public void departureDate() {
//		try {
//			WebElement departure_date=wait.until(ExpectedConditions.visibilityOfElementLocated(departureDateField));
//			departure_date.click();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		LocalDate date=LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        
        // Format the date to string with dd format
        String formattedDepartureDate = date.format(formatter);
		System.out.println(formattedDepartureDate);
		
        DateTimeFormatter formatterDD = DateTimeFormatter.ofPattern("dd");
        
        // Format the date to string with dd format
        String formattedDepartureDateDD = date.format(formatterDD);
		System.out.println(formattedDepartureDateDD);
		
		
		try {
			WebElement selectDepartureDate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='"+formattedDepartureDateDD+"']//ancestor::div[contains(@aria-label,'"+formattedDepartureDate+"')and contains(@class, 'DayPicker-Day')]")));
			System.out.println(selectDepartureDate);
			selectDepartureDate.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test(priority=8, dependsOnMethods= {"departureDate()"})
	public void returnDate() {
		try {
			WebElement selectReturnField=wait.until(ExpectedConditions.visibilityOfElementLocated(ReturnDateField));
			selectReturnField.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		LocalDate date=LocalDate.now().plusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        
        // Format the date to string with dd format
        String formattedDepartureDate = date.format(formatter);
		System.out.println(formattedDepartureDate);
		
        DateTimeFormatter formatterDD = DateTimeFormatter.ofPattern("dd");
        
        // Format the date to string with dd format
        String formattedDepartureDateDD = date.format(formatterDD);
		System.out.println(formattedDepartureDateDD);
		
		try {
			WebElement selectreturnDate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='"+formattedDepartureDateDD+"']//ancestor::div[contains(@aria-label,'"+formattedDepartureDate+"')and contains(@class, 'DayPicker-Day')]")));
			System.out.println(selectreturnDate);
			selectreturnDate.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test(priority=9, dependsOnMethods= {"returnDate()"})
	public void selectFare() {
		try {
			WebElement student_Fare=wait.until(ExpectedConditions.visibilityOfElementLocated(studentFare));
			student_Fare.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test(priority=10, dependsOnMethods= {"selectFare()"})
	public void selectFares() { 
		try {
			WebElement search_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(searchBtn));
			search_btn.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			WebElement flightText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Flights from')]")));
			Assert.assertTrue("flight text is displayed", flightText.isDisplayed());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
