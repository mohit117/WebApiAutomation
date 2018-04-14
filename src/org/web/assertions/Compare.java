package org.web.assertions;

import org.openqa.selenium.WebDriver;
import org.web.base.DriverInstance;

public class Compare extends DriverInstance{

	public static boolean validatePageUrl(WebDriver driver, String expUrl)
	{
		boolean flag = false;
		if(driver.getCurrentUrl().equalsIgnoreCase(expUrl))
		{
			flag = true;
		}
	
		return flag;
	}
	
	
	public static boolean validatePageTitle(WebDriver driver, String expTitle)
	{
		boolean flag = false;
		if(driver.getTitle().equalsIgnoreCase(expTitle))
		{
			flag = true;
		}
	
		return flag;
	}
	
}
