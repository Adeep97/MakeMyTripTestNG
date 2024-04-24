package com.makemytrip;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.utilites.BaseClass;

public class Login extends BaseClass{
	@Test
	public void Url() {
		driver.get("https://www.makemytrip.com/");
		String expected = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
		String actual=driver.getTitle();
		System.out.println(actual);
		Assert.assertTrue(expected.equals(actual));
		System.out.println("testPassed");
        
	}
	
	@Test(enabled=true)
	public void invalidTitle() {
		driver.get("https://www.makemytrip.com/");
		String expected = "MyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
		String actual=driver.getTitle();
		Assert.assertFalse(expected.equals(actual));
		System.out.println("testPassed");
	}

}
