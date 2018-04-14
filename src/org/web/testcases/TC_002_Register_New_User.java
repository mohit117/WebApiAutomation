package org.web.testcases;

import org.testng.annotations.Test;
import org.web.assertions.Compare;
import org.web.base.DriverInstance;
import org.web.datagenerators.DataGenerator;
import org.web.facebookpages.LoginPage;

public class TC_002_Register_New_User extends DriverInstance{

	
	/* below test is using static data
	@Test
	public void tc_register_new_user() throws Exception
	{
		LoginPage login = new LoginPage(driver);
		login.enterFirstName("Ramkumar");
		login.enterLastName("david");
		login.enterPassword("23234234234");
		
	}
	*/
		
	//below test will use data from excel
	
	@Test(dataProvider="dataforregisterfromexcel", dataProviderClass=DataGenerator.class)
	public void tc_register_new_user(String fname, String lname, String mnumber) throws Exception
	{
		boolean flag = Compare.validatePageTitle(driver, "facebook - log in or sign up");
		if(flag)
		{		
			LoginPage login = new LoginPage(driver);
			login.enterFirstName(fname);
			login.enterLastName(lname);
			login.enterMobileNumber(mnumber);
		}
		else
		{
			System.out.println("Page title is not matching");
		}
			
	}
	
	

	
}
