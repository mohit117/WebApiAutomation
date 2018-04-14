package org.web.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.web.assertions.Compare;
import org.web.base.DriverInstance;
import org.web.datagenerators.DataGenerator;
import org.web.facebookpages.LoginPage;

public class Tc_001_Validate_LoginFunctionality extends DriverInstance{
	
	@Test(dataProvider="dataforloginfromexcel", dataProviderClass=DataGenerator.class)	
	public void tc_login_functionality(String umane, String pass) throws Exception
	{
		LoginPage login = new LoginPage(driver);
		//Assert.assertEquals(true, validatePageUrl());
		login.enterUsername(umane);
		login.enterPassword(pass);
		login.clickSignin();
	}
	
	

}
