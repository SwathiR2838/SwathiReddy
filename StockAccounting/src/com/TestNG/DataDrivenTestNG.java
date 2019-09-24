package com.TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ExcelPractice.ExcelFileUtil;

public class DataDrivenTestNG 
{
	WebDriver driver;
	String res;
	
@BeforeTest
public void appLaunch()
{
	// Launching the stock accounting Application
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\swathi.r\\Swathi_Automation\\lib\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("http://webapp.qedge.com");
	driver.manage().window().maximize();
	
	
}
@Test(priority=1)
public String appLogin(String username,String pwd)
{
	//login functionality
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys(username);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(pwd);
	driver.findElement(By.id("btnsubmit")).click();
	
	//Validation
	if(driver.findElement(By.id("logout")).isDisplayed())
	{
		res="pass";
	}
	else
	{
		res="Fail";
	}
	return res;
}
@Test(dataProvider="getData" ,priority=2)
public void createSuplier(String sName,String address,String city,String country,String contactPerson,String pNumber,String email,String mNumber,String note) throws InterruptedException
{
	driver.findElement(By.linkText("Suppliers")).click();
	System.out.println("data driven test");
	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
	System.out.println("data driven test1");
	String expect_Resut=driver.findElement(By.id("x_Supplier_Number")).getAttribute("value");
	System.out.println(expect_Resut);
	driver.findElement(By.id("x_Supplier_Name")).sendKeys(sName);
	driver.findElement(By.id("x_Address")).sendKeys(address);
	driver.findElement(By.id("x_City")).sendKeys(city);
	driver.findElement(By.id("x_Country")).sendKeys(country);
	driver.findElement(By.id("x_Contact_Person")).sendKeys(contactPerson);
	driver.findElement(By.id("x_Phone_Number")).sendKeys(pNumber);
	driver.findElement(By.id("x__Email")).sendKeys(email);
	driver.findElement(By.id("x_Mobile_Number")).sendKeys(mNumber);
	driver.findElement(By.id("x_Notes")).sendKeys(note);
	Actions pageDown=new Actions(driver);
	pageDown.sendKeys(Keys.PAGE_DOWN).build().perform();
	Thread.sleep(1000);
	driver.findElement(By.id("btnAction")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[text()='OK!']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
	
	//validation
	
	if(driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).isDisplayed())
	{
	
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
		driver.findElement(By.id("psearch")).clear();

		driver.findElement(By.id("psearch")).sendKeys(expect_Resut);
		driver.findElement(By.id("btnsubmit")).click();
	}
	else
	{Thread.sleep(1000);
		driver.findElement(By.id("psearch")).clear();
		driver.findElement(By.id("psearch")).sendKeys(expect_Resut);
		driver.findElement(By.id("btnsubmit")).click();
	}
	String actual_result=driver.findElement(By.xpath("//*[@id='el1_a_suppliers_Supplier_Number']/span")).getText();
	System.out.println(actual_result);
	Assert.assertEquals(actual_result, expect_Resut);	
	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
	driver.findElement(By.id("psearch")).clear();                           
}

@DataProvider
public Object[][] getData() throws Throwable
{
	Object[][] data=new Object[2][9];
	ExcelFileUtil excel=new ExcelFileUtil();
	for (int i = 0; i <=excel.rowCount("Suplier"); i++) 
	{
		for (int j = 0; j < excel.colCount("Suplier", 1); j++) 
		{
			data[i][j]=excel.getData("Suplier", i, j);
		}
	}
	return data;
}
	
}
