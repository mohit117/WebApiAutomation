package org.web.testcases;

import org.testng.annotations.Test;
import org.web.base.DriverInstance;
import org.web.facebookpages.LoginPage;

public class Tc_001_Validate_LoginFunctionality extends DriverInstance{
	
	@Test	
	public void tc_login_functionality() throws Exception
	{
		LoginPage login = new LoginPage(driver);
		login.enterUsername("mohit");
		login.enterPassword("pass");
		login.clickSignin();
	}
	
	
	public void testDataGenerator()
	{
		
		
	}
	
	

}
