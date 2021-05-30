package com.qa.base;

import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Actions actions ;
	public static Properties configFile;
	public static WebDriverWait wait;
	public Robot robot;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase(){
		
		FileInputStream proFile;
		configFile = new Properties();
		    try {
				proFile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
				configFile.load(proFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    
	}
	
	public static WebDriver launchBrowser(String browsername, String url){
		
			switch (browsername){
			case "chrome" :
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\Resources\\chromedriver90.exe");
				ChromeOptions options = new ChromeOptions(); 
				options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
				
				Map<String, Object> prefs = new HashMap<String, Object>();
			    prefs.put("credentials_enable_service", false);
			    prefs.put("profile.password_manager_enabled", false);
			    options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				break;

			case "firefox":		
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "\\Resources\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;

			case "ie":
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "\\Resources\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
			}
		
			
			e_driver = new EventFiringWebDriver(driver);
			// Now create object of EventListerHandler to register it with EventFiringWebDriver
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			return driver;
	}
	
	public static String getProperty(String attribute){
		return configFile.getProperty(attribute);
	}
}

