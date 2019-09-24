package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadDataPropertiesFile {

	public static void main(String[] args) throws Exception {
		
	Properties configProp=new Properties();
	FileInputStream fis =new FileInputStream("C:\\Users\\swathi.r\\workspace\\StockAccounting\\PropertyFile\\Environment.properties");
	configProp.load(fis);
	System.out.println(configProp.getProperty("Browser"));
	System.out.println(configProp.getProperty("URL"));
	System.out.println(configProp.getProperty("UserName"));
	System.out.println(configProp.getProperty("Password"));
	}
}
