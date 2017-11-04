package org.web.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utility {

	public static Object fetchProperty(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("./Config/webconfig.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		return prop.get(key);
	}
	
}
