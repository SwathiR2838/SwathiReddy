package com.JavaBasics.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaBasics implements JavaInterfaceOne, JavaInterfaceTwo {
	public void methodOne()
	{
		System.out.println("Hello Class Method");
	
	}
	@Override
	public void interfaceMethod() {
		// TODO Auto-generated method stub
		System.out.println("Interface Method");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   JavaBasics basic=new JavaBasics();
   basic.methodOne();
   basic.interfaceMethod(); //conflict bw two interface methods
   
   JavaInterfaceTwo interfaceTwo=new JavaBasics();
   interfaceTwo.interfaceMethod();
   System.setProperty("webdriver.chrome.driver","C:\\Users\\swathi.r\\Swathi_Automation\\lib\\chromedriver.exe" );

   WebDriver driver=new ChromeDriver();
   driver.get("https://www.google.com/");
  
	}
	
}
