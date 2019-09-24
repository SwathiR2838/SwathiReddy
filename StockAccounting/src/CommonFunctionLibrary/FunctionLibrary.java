package CommonFunctionLibrary;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;

public class FunctionLibrary 
{
	static WebDriver driver;
	
	//startBrowser
	public static WebDriver startBrowser(WebDriver driver) throws Exception
	{
		if(PropertyFileUtil.getValue("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "");
			driver=new FirefoxDriver();
		}else
			if(PropertyFileUtil.getValue("Browser").equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\swathi.r\\Downloads\\chromedriver.exe");
				driver=new ChromeDriver();
			}else
				if(PropertyFileUtil.getValue("Browser").equalsIgnoreCase("ie"))
				{
					System.setProperty("webdriver.ie.driver", "");
					driver=new InternetExplorerDriver();
				}
		return driver;		
	}
	
	//openApplication
	
	public static void openApplication(WebDriver driver) throws Exception
	{
		driver.get(PropertyFileUtil.getValue("URL"));
		driver.manage().window().maximize();
	}
	
	//clickAction
	public static void clickAction(WebDriver driver,String locatorType,String locatorValue)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).click();
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).click();
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).click();
				}else
					if(locatorType.equalsIgnoreCase("linkText"))
					{
						driver.findElement(By.linkText(locatorValue)).click();
					}	
	}
	
	//typeAction
	public static void typeAction(WebDriver driver,String locatorType,String locatorValue,String testData)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(testData);
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(testData);
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).clear();
					driver.findElement(By.xpath(locatorValue)).sendKeys(testData);
				}else
					if(locatorType.equalsIgnoreCase("linkText"))
					{
						driver.findElement(By.linkText(locatorValue)).clear();
						driver.findElement(By.linkText(locatorValue)).sendKeys(testData);
					}
	}
	
	//CaptureData
		public static void captureData(WebDriver driver,String locatorType,String locatorValue) throws IOException 
		{
			String data="";
			
			if(locatorType.equalsIgnoreCase("id"))
			{
				data=driver.findElement(By.id(locatorValue)).getAttribute("value");
			}else
				if(locatorType.equalsIgnoreCase("name"))
				{
					data=driver.findElement(By.name(locatorValue)).getAttribute("value");
				}else
					if(locatorType.equalsIgnoreCase("xpath"))
					{
						data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
					}
			
			FileWriter fw=new FileWriter("C:\\Users\\swathi.r\\workspace\\StockAccounting\\CaptureData\\CaptureData.txt");
			
			BufferedWriter bw=new BufferedWriter(fw);
			
			bw.write(data);
			bw.flush();
			bw.close();
			
		}
	
		//table validation
		public static void tableValidations(WebDriver driver,String coloumn) throws Throwable
		{
			//reading data from text file
			System.out.println("tableValidation");
			FileReader fr=new FileReader("C:\\Users\\swathi.r\\workspace\\StockAccounting\\CaptureData\\CaptureData.txt");
			BufferedReader br=new BufferedReader(fr);
			String exp_Data=br.readLine();
			
			//Converting String Value into integer
			int colNum=Integer.parseInt(coloumn);
			if(driver.findElement(By.xpath(PropertyFileUtil.getValue("Search.Panel"))).isDisplayed())
			{
				driver.findElement(By.xpath(PropertyFileUtil.getValue("Search.Panel"))).click();
				Thread.sleep(1000);
				driver.findElement(By.id(PropertyFileUtil.getValue("Search.Box"))).clear();
				driver.findElement(By.id(PropertyFileUtil.getValue("Search.Box"))).sendKeys(exp_Data);
				driver.findElement(By.id(PropertyFileUtil.getValue("Search.Button"))).click();
			}else
			{
				driver.findElement(By.id(PropertyFileUtil.getValue("Search.Box"))).clear();
				driver.findElement(By.id(PropertyFileUtil.getValue("Search.Box"))).sendKeys(exp_Data);
				driver.findElement(By.id(PropertyFileUtil.getValue("Search.Button"))).click();
			}
			
			//Suplier Webtable
			WebElement webtab=driver.findElement(By.xpath(PropertyFileUtil.getValue("webtable")));
			
			//row Count
			List<WebElement> rows=webtab.findElements(By.tagName("tr"));
			
			for (int i = 0; i <=rows.size(); i++)
			{
				//Capturing Suplier Number
				
				String actData=driver.findElement(By.xpath("//*[@id='ewContentColumn']"+"/div[3]/form/div//"+"table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colNum+"]"+"/div/span")).getText();
				System.out.println(actData);
				
				//VAlidation
				Assert.assertEquals(exp_Data, actData);
				break;
			}
			
		}
	
	//waitForElement
	public static void waitForElement(WebDriver driver,String locatorType,String locatorValue,String waitTime)
	{
		WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(waitTime));
		if(locatorType.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				}else
					if(locatorType.equalsIgnoreCase("linkText"))
					{
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
					}
	
	}
	
	//To generate Date
	public static String generateDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
	}
	
	
	
	//Scroll Down page
	public static void pageDown(WebDriver driver)
	{
		Actions pageDown=new Actions(driver);
		pageDown.sendKeys(Keys.PAGE_DOWN).build().perform();
	}
	
	//close browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	public static void main(String[] args) throws Exception 
	{
		
		FunctionLibrary.startBrowser(driver);
		FunctionLibrary.openApplication(driver);
		FunctionLibrary.typeAction(driver, "id", "username", "admin");
		FunctionLibrary.waitForElement(driver, "id", "password", "10");
		FunctionLibrary.typeAction(driver, "id", "password", "master");
		FunctionLibrary.waitForElement(driver, "name", "btnsubmit", "10");
		FunctionLibrary.clickAction(driver, "name", "btnsubmit");
		
	}
}
