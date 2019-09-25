package StepDefinetion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class loginStepDefinition
{
	WebDriver driver;
	@Given("^Open Browser$")
	public void open_Browser() throws Throwable {
	  
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\swathi.r\\Swathi_Automation\\lib\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
	}

	@When("^Enter the url \"([^\"]*)\"$")
	public void enter_the_url(String arg1) throws Throwable {
		driver.get("http://webapp.qedge.com");
	}

	@When("^Enter Username and Password$")
	public void enter_Username_and_Password() throws Throwable {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("master");
	}

	@When("^Click on Login$")
	public void click_on_Login() throws Throwable {
		driver.findElement(By.id("btnsubmit")).click();
	}

	@Then("^must Login Successfully$")
	public void must_Login_Successfully() throws Throwable 
	{
	   System.out.println("succces");
	}

}
