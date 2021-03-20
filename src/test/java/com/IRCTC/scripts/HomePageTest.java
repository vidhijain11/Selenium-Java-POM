package com.IRCTC.scripts;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AlertPage;
import com.qa.pages.HomePage;
import com.qa.util.Utilities;

public class HomePageTest extends TestBase{
	
	AlertPage alertPage;
	HomePage homePage;
	String sheetName= "HomePage";
	
 @BeforeMethod
 public void setUp(){
	 
	 launchBrowser(getProperty("browser"), getProperty("url"));
	 alertPage = new AlertPage();
	 homePage= alertPage.clickOK();
 }
 
 @Test
 public void selectTo() {
	 homePage.selectTo("NEW DELHI - NDLS");
	 Assert.assertEquals(homePage.getToStation(),"NEW DELHI - NDLS");
 }
 
 //@Test(dataProvider="getHomePageData", description="Search train")
  public void bookTicket(String fromStation,String toStation, String journeyQuota, String date, String  journeyClass) throws InterruptedException {
	  homePage.selectFrom(fromStation);
	  Assert.assertEquals(homePage.getFromStation(), fromStation);
	  
	  homePage.selectTo(toStation);
	  Assert.assertEquals(homePage.getToStation(),toStation);
	  
	  homePage.selectJourneyQuota(journeyQuota);
	  Assert.assertTrue(homePage.validateQuotaNClass(journeyQuota));
	  
	  homePage.selectJourneyClass(journeyClass);
	  Assert.assertTrue(homePage.validateQuotaNClass(journeyClass));
	  
	  homePage.setDate(date);
	  Assert.assertEquals(homePage.getDate(), date);
	  
	  homePage.checkDivyaangConcession(false);
	  homePage.clickSearch();
  }

 @DataProvider
 public Object[][] getHomePageData(){
	 return Utilities.getTestData(sheetName);
	 
 }
 
  @AfterMethod()
  public void tearDown(){
	  driver.quit();
  }
  
  
}
