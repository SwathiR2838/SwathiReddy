package DriverFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.*;
import CommonFunctionLibrary.FunctionLibrary;
import ExcelPractice.ExcelFileUtil;

public class DriverScript 
{
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
public void startTest() throws Throwable
{
	
	ExcelFileUtil excel=new ExcelFileUtil();
	for(int i=1;i<=excel.rowCount("MasterTestCases");i++)
	{
		String ModuleStatus="";
		if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
		{
			System.out.println(excel.getData("MasterTestCases", i, 2));
			//Define Module Name
			
			
			String TCModule=excel.getData("MasterTestCases", i, 1);
			report=new ExtentReports("C:\\Users\\swathi.r\\workspace\\StockAccounting\\Reports\\"+TCModule+FunctionLibrary.generateDate()+".html");
			logger=report.startTest(TCModule);
			
			int rowcount=excel.rowCount(TCModule);
			
			System.out.println(rowcount);
			//TCModule Sheet
			for(int j=1;j<rowcount;j++)
			{
				String description=excel.getData(TCModule, j, 0);
				String object_Type=excel.getData(TCModule, j, 1);
				String locator_Type=excel.getData(TCModule, j, 2);
				String locator_Value=excel.getData(TCModule, j, 3);
				String test_Data=excel.getData(TCModule, j, 4);
				
				System.out.println(description);
				System.out.println(object_Type);
				System.out.println(locator_Type);
				try
				{
				if(object_Type.equalsIgnoreCase("startBrowser"))
				{
					driver=FunctionLibrary.startBrowser(driver);
					logger.log(LogStatus.INFO, description);
				}
				if(object_Type.equalsIgnoreCase("openApplication"))
				{
					FunctionLibrary.openApplication(driver);
					logger.log(LogStatus.INFO, description);
				}
				if(object_Type.equalsIgnoreCase("typeAction"))
				{
					FunctionLibrary.typeAction(driver, locator_Type, locator_Value, test_Data);
					logger.log(LogStatus.INFO, description);
				}
				if(object_Type.equalsIgnoreCase("clickAction"))
				{
					FunctionLibrary.clickAction(driver, locator_Type, locator_Value);
					logger.log(LogStatus.INFO, description);
				}
				if(object_Type.equalsIgnoreCase("waitForElement"))
				{
					FunctionLibrary.waitForElement(driver, locator_Type, locator_Value, test_Data);
					logger.log(LogStatus.INFO, description);
				}
				if(object_Type.equalsIgnoreCase("pageDown"))
				{
					FunctionLibrary.pageDown(driver);
					logger.log(LogStatus.INFO, description);
				}
				if(object_Type.equalsIgnoreCase("captureData"))
				{
					FunctionLibrary.captureData(driver, locator_Type, locator_Value);
					logger.log(LogStatus.INFO, description);
				}
				if(object_Type.equalsIgnoreCase("tableValidations"))
				{
					FunctionLibrary.tableValidations(driver, "1");
					logger.log(LogStatus.INFO, description);
				}
				////Status update in TCModule Sheet "pass"
				excel.setData(TCModule, j, 5,"PASS");
				ModuleStatus="true";
				}
				catch(Exception e)
				{
				   excel.setData(TCModule, j,5, "FAIL");
				   logger.log(LogStatus.FAIL, description+" FAIL");
					ModuleStatus="false";
					File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);				
					FileUtils.copyFile(srcFile, new File("C:\\Users\\swathi.r\\workspace\\StockAccounting\\ScreenShots\\"+TCModule+" "+FunctionLibrary.generateDate()+".png"));
					break;
				}
			}
				if(ModuleStatus.equalsIgnoreCase("true"))
				{
				//	excel.setData("MasterTestcases", i, 3, "PASS");
				}else
					if(ModuleStatus.equalsIgnoreCase("false"))
					{
						excel.setData("MasterTestcases", i, 3, "FAIL");
					}
	report.endTest(logger);
	report.flush();
			}else
			{
				excel.setData("MasterTestcases", i, 3, "Not executed");
			}
		}
		
	}
}

