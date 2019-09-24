package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

import CommonFunctionLibrary.StockAccountingLibrary;

public class PropertyFileUtil 
{
public static String getValue(String key) throws Exception
{
	 Properties configProp=new Properties();
	 FileInputStream fis=new FileInputStream("C:\\Users\\swathi.r\\workspace\\StockAccounting\\PropertyFile\\Environment.properties");
	 configProp.load(fis);
	String value= configProp.getProperty(key);
	return value;
}

 public static void main(String args[]) throws Exception
 {
	 StockAccountingLibrary fun=new StockAccountingLibrary();
	fun.appLaunch(PropertyFileUtil.getValue("URL"));
	fun.appLogin(PropertyFileUtil.getValue("UserName"), PropertyFileUtil.getValue("Password"));
	fun.appLogout();
	fun.appClose();
	
 }
}
