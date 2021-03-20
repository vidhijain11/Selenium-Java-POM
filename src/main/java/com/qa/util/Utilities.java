package com.qa.util;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.report.ExtentReporterNG;
import com.sun.media.sound.InvalidFormatException;

public class Utilities extends TestBase {
	
	final static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/main/java/com/qa/testData/IRCTCTestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	public Utilities(){
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyElementDisplayed(WebElement element){
		boolean flag = false;
		try {
			element.isDisplayed();
			flag= true;
		}
		catch (Exception e) {
			e.printStackTrace();
			flag= false;
		}
		return flag;
	}

	public void sleepSel (int milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void closeBrowser() {
		driver.quit();
		System.out.println("Browser closed");
	}
	
	public void waitUntilVisible(WebElement element, int timeout) {

		for (int i=0; i<=timeout; i++) {

			if (element.isDisplayed()) {
				break;
			}
			sleepSel(1000);
			System.out.println("Element is not displayed "+i);
		}
	}
	

	 public void closeProcesses(String[] processName){
		 
	     for(String process : processName){
		 
		   try {
			 Runtime.getRuntime().exec("taskkill /F /IM "+ process);
		   } catch (IOException e) {
			e.printStackTrace();
		   }
	     }
	 }

	 public static String takeScreenshotAtEndOfTest(){
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String destFile = ExtentReporterNG.extentReportFilePath + "/screenshots/" + System.currentTimeMillis() + ".png";
			try {
				FileUtils.copyFile(scrFile, new File(destFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destFile.replace("/", "\\");
		}
	 
	public static Object[][] getTestData(String sheetName) {
			FileInputStream file = null;
			try {
				file = new FileInputStream(TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				try {
					book = WorkbookFactory.create(file);
				} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			System.out.println(sheet.getLastRowNum() + "--------" +
			 sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}
			return data;
	}
	

	/**
	 * This function returns date in format 2018_07_24 09_07_17 
	 * @return
	 */
	public static String dateTimeNow() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}