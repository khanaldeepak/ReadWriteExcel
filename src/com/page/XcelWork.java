package com.page;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;

public class XcelWork {
	WebDriver Driver;
	Xls_Reader Testcase=new Xls_Reader(PropertiesUtil.getProperty("TestData"));
	Xls_Reader Result=new Xls_Reader(PropertiesUtil.getProperty("TestResult"));
	HomePage Home;
	SignIn Sign;
	
  @Test
  public void InvalidSignUp() {
	  int count = Testcase.rowcnt("Sheet1");
	  
	  for(int c=2;c<=count;c++){
		
		  Driver.get(PropertiesUtil.getProperty("TestURL"));
		  
		  Driver.manage().window().maximize(); 
		  
	  String uname = Testcase.getCellData("Sheet1","Email",c);
	  System.out.println(uname);
	  
	 Home = new HomePage(Driver);
	 Home.SignIn();
	 Sign = new SignIn(Driver);
	 Sign.Invalid(uname);
	 String colors= Sign.color();
	 String colorss = Testcase.getCellData("Sheet1","Expected",c);
	
	int columnnum = Testcase.getcollam("Sheet1", "Status");
	if (colors.equals(colorss)){
		
		Testcase.setResultData("Sheet1",columnnum, c, "PASS");
		
	}else{
		Testcase.setResultData("Sheet1",columnnum, c, "FAIL");
		
	}
	 
	  } 
  }
  
  
  @BeforeTest
  public void beforeTest() {
		
	  String browser = PropertiesUtil.getProperty("Browser");
	  
	  if(browser.equalsIgnoreCase("Firefox")){
		  Driver = new FirefoxDriver();  
	  }else if(browser.equalsIgnoreCase("Chrome")){
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Samima\\workspace\\chromedriver.exe");
		  Driver = new ChromeDriver();
	  }else if(browser.equalsIgnoreCase("IE")){
		  System.setProperty("webdriver.ie.driver", "C:\\Users\\Samima\\workspace\\IEDriverServer.exe");
		  Driver = new InternetExplorerDriver();
	  }else{
		  System.out.println("This Framework Does not support that Browser");
	  }
  
  }
  @AfterTest
  public void afterTest() {
   Driver.close();
  }

}
