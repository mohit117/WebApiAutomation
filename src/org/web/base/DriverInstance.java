package org.web.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.web.utility.Utility;

public class DriverInstance {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void initiateDriverInstance() throws IOException
	{
		if(Utility.fetchProperty("browserName").toString().equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/.Drivers/chromedriver.exe");
			driver = new ChromeDriver();			
		}
		else if(Utility.fetchProperty("browserName").toString().equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "/.Drivers/geckodriver.exe");
			driver = new FirefoxDriver();			
		}
		else if(Utility.fetchProperty("browserName").toString().equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "/.Drivers/internetexplorerdriver.exe");
			driver = new InternetExplorerDriver();			
		}
		else
		{
			System.setProperty("webdriver.chrome.driver", "/.Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.get(Utility.fetchProperty("applicationUrl").toString());
	}

	@AfterMethod
	public void closeDriverInstance()
	{
		
		driver.quit();
	}

}
