package com.TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ExcelPractice.ExcelFileUtil;

public class ParametrizationTestNG 
{
	WebDriver driver;

@Test(dataProvider="getData")
public void appLogin(String username,String pwd)
{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\swathi.r\\Swathi_Automation\\lib\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("http://webapp.qedge.com");
	driver.manage().window().maximize();
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys(username);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(pwd);
	driver.findElement(By.id("btnsubmit")).click();
	driver.close();
}
//
//@DataProvider
//public Object[][] getData()
//{
//	Object[][] data=new Object[1][2];
//	data[0][0]="admin";
//	data[0][1]="master";
//	return data;
//}

@DataProvider
public Object[][] getData() throws Throwable
{
	ExcelFileUtil futil=new ExcelFileUtil();
	System.out.println(futil.rowCount("InputSheet"));
	Object[][] data=new Object[2][2];
	return data;
}
}
