package com.IRCTC.scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AlertPage;

public class AlertPageTest {
	TestBase tb = new TestBase();
	AlertPage alertPage;
	WebDriver driver;
	
	 @BeforeMethod
	 @Parameters("browser")
	 public void setUp(String browser){
		 driver = TestBase.launchBrowser(browser, TestBase.getProperty("url"));
		 alertPage = new AlertPage(driver);	 
		 }
	@Test
	public void validateAlert()
	{
		alertPage.validateAlert();
		alertPage.clickOK();
	}
	
	@AfterMethod()
	public void tearDown(){
		  driver.quit();
	}

}


