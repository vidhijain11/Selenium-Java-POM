package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;

public class HomePage {
	WebDriver driver;
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	@FindBy (xpath="//*[@id='origin']") 
	WebElement txtFrom;
	
	@FindBy (xpath="//*[@id='destination']")
	WebElement txtTo;
	
	@FindBy (xpath="//*[@id='origin']//*[@aria-autocomplete='list'][@role='searchbox']") 
	WebElement txtSearchFrom;
	
	@FindBy (xpath="//*[@id='destination']//*[@aria-autocomplete='list'][@role='searchbox']")
	WebElement txtSearchTo;
	
	@FindBy (xpath="//*[@id='journeyQuota']//div[@role='button']") 
	WebElement ddJourneyQuota;
	
	@FindBy (xpath="//*[@id='journeyClass']//div[@role='button']")
	WebElement ddJourneyClass;
	
	@FindBy (xpath="//*[contains(@class,'ui-calendar')]//input") 
	WebElement txtDate;
	
	@FindBy (xpath="//label[contains(text(),'Divyaang Concession')]")
	WebElement checkConsession;
	
	@FindBy (xpath="//*[@class='search_btn train_Search']")
	WebElement btnSearch;
	
	@FindBy(xpath="//span[contains(text(),'OK')]")
	WebElement btnConfirmOK;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Select any option from drop-down
	 * @param option
	 * @return
	 */
	public WebElement selectOption(String option){
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[contains(@class,'ng-star-inserted')][text()='"+option+"']"))));
		return driver.findElement(By.xpath("//span[contains(@class,'ng-star-inserted')][text()='"+option+"']")) ;
	}
	
	/**
	 * Select value From station
	 * @param fromStation
	 */
	public void selectFrom(String fromStation){
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(txtFrom));
		txtFrom.click();
		txtSearchFrom.sendKeys(fromStation);
		selectOption(fromStation).click();
	}
	
	/**
	 * select value To station
	 * @param toStation
	 */
	public void selectTo(String toStation){
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(txtTo));
		txtTo.click();
		txtSearchTo.sendKeys(toStation);
		selectOption(toStation).click();
	}
	
	/**
	 * select journey quota from drop down
	 * @param journeyQuota
	 */
	public void selectJourneyQuota(String journeyQuota){
		ddJourneyQuota.click();
		selectOption(journeyQuota).click();
		if(journeyQuota.equals("DIVYAANG")|| journeyQuota.equals("LOWER BERTH/SR.CITIZEN")){
			btnConfirmOK.click();
		}
	}
	
	/**
	 * select journey class from drop down
	 * @param journeyClass
	 */
	public void selectJourneyClass(String journeyClass){
		ddJourneyClass.click();
		selectOption(journeyClass).click();
	}
	
	/**
	 * Enter date of Journey
	 * @param dateDDMMYYYY
	 */
	public void setDate(String dateDDMMYYYY){
		txtDate.click(); 
		txtDate.sendKeys(Keys.CONTROL + "a");
		txtDate.sendKeys(Keys.BACK_SPACE);
		txtDate.sendKeys(dateDDMMYYYY);
		
	}
	
	/**
	 * check/uncheck Divyaang concession
	 * @param check
	 */
	public void checkDivyaangConcession(boolean check){
		Actions ac = new Actions(driver);
		 if(checkConsession.isSelected() && check==false){
			 ac.click(checkConsession).build().perform();
		 }else if(!checkConsession.isSelected() && check==true){
			 ac.click(checkConsession).build().perform();
		 }
	}
	
	/**
	 * click on search button
	 */
	public void clickSearch(){
		btnSearch.click();
	}
	
	/**
	 * validate journey quota selected successfully 
	 * @param quotaClass
	 * @return boolean
	 */
	public boolean validateQuotaNClass(String quotaClass){
		return driver.findElement(By.xpath("//span[contains(text(),'"+quotaClass+"')]")).isDisplayed();
	}
	
	/**
	 * get To station selected value
	 * @return
	 */
	public String getToStation(){
		return (String)jse.executeScript("var data = arguments[0].value; return data;", txtSearchTo);
	}
	
	/**
	 * get From station selected value
	 * @return
	 */
	public String getFromStation(){
		return (String)jse.executeScript("var data = arguments[0].value; return data;", txtSearchFrom);
	}
	
	/**
	 * get selected date
	 * @return
	 */
	public String getDate(){
		return (String)jse.executeScript("var data = arguments[0].value; return data;", txtDate);
	}
	
}
