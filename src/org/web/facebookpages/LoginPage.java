package org.web.facebookpages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.web.utility.Utility;
import org.web.assertions.Compare;
import org.web.base.DriverInstance;

public class LoginPage extends DriverInstance{
	
	WebDriver driver;

	public LoginPage(WebDriver driver)
	{
		
		this.driver = driver;
	}
	
	
	
	public void enterUsername(String uname) throws Exception
	{
				
		driver.findElement(By.id(Utility.fetchLocatorValue("login_username_id"))).sendKeys(uname);
	}
	
	
	public void enterPassword(String pass) throws Exception
	{
		driver.findElement(By.id(Utility.fetchLocatorValue("login_pass_id"))).sendKeys(pass);
		
	}
	
	public void clickSignin() throws Exception
	{
		driver.findElement(By.xpath(Utility.fetchLocatorValue("login_button_id"))).click();
		
	}
	
	public void enterFirstName(String firstname) throws Exception
	{
		
		driver.findElement(By.xpath(Utility.fetchLocatorValue("login_first_name"))).sendKeys(firstname);
	}
	
	public void enterLastName(String lastname) throws Exception
	{
		
		driver.findElement(By.xpath(Utility.fetchLocatorValue("login_last_name"))).sendKeys(lastname);
	}
	
	public void enterMobileNumber(String mobno) throws Exception
	{
		
		driver.findElement(By.xpath(Utility.fetchLocatorValue("login_mob_number"))).sendKeys(mobno);
	}
	
}
