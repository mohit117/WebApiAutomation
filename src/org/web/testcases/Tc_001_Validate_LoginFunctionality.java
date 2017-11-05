package org.web.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.web.base.DriverInstance;
import org.web.facebookpages.LoginPage;

public class Tc_001_Validate_LoginFunctionality extends DriverInstance{
	
	@Test(dataProvider="datafromexcel")	
	public void tc_login_functionality(String umane, String pass) throws Exception
	{
		LoginPage login = new LoginPage(driver);
		login.enterUsername(umane);
		login.enterPassword(pass);
		login.clickSignin();
	}
	
	@DataProvider(name="Static")
	public Object[][] testDataGenerator()
	{
		Object[][] data = {{"mohit","pass1"},{"devanshi","pass2"},{"ramkumar","pass3"}};
		return data;
		
	}
	
	
	@DataProvider(name="datafromexcel")
	public Object[][] testDataGeneratorFromExcel() throws Exception
	{
		FileInputStream fis = new FileInputStream("./TestData/TestData.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet loginSheet = workbook.getSheet("Login");
		int noOfrows = loginSheet.getPhysicalNumberOfRows();
		
		Object [][] testdata = new Object [noOfrows][2];
		
		for(int i=0; i<noOfrows; i++)
		{
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell username = row.getCell(0);
			XSSFCell password = row.getCell(1);
			
			testdata[i][0] = username.getStringCellValue();
			testdata[i][1] = password.getStringCellValue();
		}
		
		return testdata;
		
	}
	

}
