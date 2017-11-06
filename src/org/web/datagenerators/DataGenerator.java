package org.web.datagenerators;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataGenerator {
	
	@DataProvider(name="Static")
	public static Object[][] testDataGenerator()
	{
		Object[][] data = {{"mohit","pass1"},{"devanshi","pass2"},{"ramkumar","pass3"}};
		return data;
		
	}
	
	
	@DataProvider(name="dataforloginfromexcel")
	public static Object[][] testDataGeneratorFromExcel() throws Exception
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
	
	@DataProvider(name="dataforregisterfromexcel")
	public static Object[][] testDataGeneratorFromExcelforRegister() throws Exception
	{
		FileInputStream fis = new FileInputStream("./TestData/TestData.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet loginSheet = workbook.getSheet("Register");
		int noOfrows = loginSheet.getPhysicalNumberOfRows();
		
		//DataFormatter formatter = new DataFormatter();
		
		Object [][] testdata = new Object [noOfrows][3];
		
		for(int i=0; i<noOfrows; i++)
		{
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell fitstname = row.getCell(0);
			XSSFCell lastname = row.getCell(1);
			XSSFCell mobnumber = row.getCell(2);
			
		//converting mobile number cell to string so that we can use getStringCellValue method to read the data.
			if(mobnumber.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				mobnumber.setCellType(Cell.CELL_TYPE_STRING);
		}
			
			testdata[i][0] = fitstname.getStringCellValue();
			testdata[i][1] = lastname.getStringCellValue();
			testdata[i][2] = mobnumber.getStringCellValue();
			// testdata[i][2] = mobnumber.getRawValue(); use this line when we are not converting mobile number cell to string type 
			
		}
		
		return testdata;
		
	}
	
}
