package com.IRCTC.scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AlertPage;

public class AlertPageTest extends TestBase {

	AlertPage alertPage;
	
	@BeforeMethod
	 public void setUp(){
		 launchBrowser(getProperty("browser"), getProperty("url"));
		 alertPage = new AlertPage();	 
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


