package org.web.facebookpages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.web.utility.Utility;

public class LoginPage {
	
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
	
}
