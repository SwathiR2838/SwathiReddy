package CommonFunctionLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateSuplier {
	WebDriver driver;
	String res;
public String appLaunch(String url)
{
	// Launching the stock accounting Application
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\swathi.r\\Swathi_Automation\\lib\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	
	//Validation
	
	if(driver.findElement(By.id("username")).isDisplayed())
	{
		res="pass";
	}
	else
	{
		res="fail";
	}
	return res;
}
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

//***********Supplier Creation*********

//public void navigateToSuplier()
//{
//	driver.findElement(By.linkText("Suppliers")).click();	
//}
public String createSuplier(String sName,String address,String city,String country,String contactPerson,String pNumber,String email,String mNumber,String note) throws InterruptedException
{
	driver.findElement(By.xpath("//*[@id='mi_a_suppliers']")).click();
	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
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
	{		
		//driver.findElement(By.id("btnsubmit")).click();
		driver.findElement(By.id("psearch")).clear();
		driver.findElement(By.id("psearch")).sendKeys(expect_Resut);
		driver.findElement(By.id("btnsubmit")).click();
	}
	String actual_result=driver.findElement(By.xpath("//*[@id='el1_a_suppliers_Supplier_Number']/span")).getText();
	System.out.println(actual_result);
	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
	driver.findElement(By.id("psearch")).clear();
	if(expect_Resut.equals(actual_result))
	{
		res="pass";
	}
	else
	{
		res="fail";
	}
	return res;	
	
}
public String stockItemCreation(String catName) throws InterruptedException
{
	Actions actions = new Actions(driver);
	actions.moveToElement(driver.findElement(By.id("mi_a_stock_items"))).perform();
	driver.findElement(By.id("mi_a_stock_categories")).click();
	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")).click();
	driver.findElement(By.id("x_Category_Name")).sendKeys(catName);
	driver.findElement(By.id("btnAction")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[text()='OK!']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
	
	if(driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).isDisplayed())
	{
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
		driver.findElement(By.id("psearch")).sendKeys(catName);
		driver.findElement(By.id("btnsubmit")).click();
	}
	else
	{
		driver.findElement(By.id("psearch")).sendKeys(catName);
		driver.findElement(By.id("btnsubmit")).click();
	}
	String actualDat=driver.findElement(By.xpath("//*[@id='el1_a_stock_categories_Category_Name']/span")).getText();
	if(catName.equals(actualDat))
	{
		res="pass";
	}
	else
	{
		res="fail";
	}
	return res;
}
public String appLogout() throws InterruptedException
{
	// Logout from the Application
	driver.findElement(By.id("logout")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[text()='OK!']")).click();
	
	//validation
	if(driver.findElement(By.id("username")).isDisplayed())
	{
		res="pass";
	}
	else
	{
		res="fail";
	}
	return res;
}
public void appClose()
{
	driver.close();
}
public static void main(String[] args) throws InterruptedException {
	
	CreateSuplier suplier=new CreateSuplier();
	String launch=suplier.appLaunch("http://webapp.qedge.com/login.php");
	System.out.println(launch);
	String login=suplier.appLogin("admin", "master");
	System.out.println(login);
	//suplier.navigateToSuplier();
	String result=suplier.createSuplier("swathi","ameerpet","hyd","India","Shrava Reddy","1234567890","swathi@gmail.com","8341392389","To do list");
	System.out.println(result);
	String stockName=suplier.stockItemCreation("SamsungJ7");
	System.out.println("Stock Item Name:"+stockName);
	String logout=suplier.appLogout();
		System.out.println(logout);
		suplier.appClose();
}

}
