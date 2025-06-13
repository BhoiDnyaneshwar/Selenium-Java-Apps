package com.rahul.pages;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MS_Verification extends XMLParsing{

	public static  WebDriver driver;
	public String userEmailTxtBox="userEmail";
	public String userPasswordTxtBox="userPassword";
	public String loginBtn="login";
	
	public MS_Verification() {
		
	}
	
	@BeforeClass
	@Before
	public void launchingUrl() {
	String browserName=System.getProperty("browser")!=null  ? System.getProperty("browser") :"PropValue";
	System.out.println(browserName);
		if(configXMLReader("browser1","webbrowser").equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(configXMLReader("url","appurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}
	}
	
	
	public WebDriver MSVerificationLogin() {
		String email=configXMLReader("testAccount","email");
		String password=configXMLReader("testAccount","password");
		WebElement emailBox=driver.findElement(By.id(userEmailTxtBox));
		WebElement passBox=driver.findElement(By.id(userPasswordTxtBox));
		String deleteSearchCriteria=Keys.chord(Keys.CONTROL,"A",Keys.DELETE);
		emailBox.sendKeys(deleteSearchCriteria,email);
		passBox.sendKeys(deleteSearchCriteria,password);
		driver.findElement(By.id(loginBtn)).click();
		return driver;
	}
	
	
	  public void invalidUserLogin(HashMap<String, String> input) { 
			WebElement emailBox = driver.findElement(By.id(userEmailTxtBox));
			WebElement passBox = driver.findElement(By.id(userPasswordTxtBox));
			String deleteSearchCriteria = Keys.chord(Keys.CONTROL, "A", Keys.DELETE);
			emailBox.sendKeys(deleteSearchCriteria, input.get("email"));
			passBox.sendKeys(deleteSearchCriteria, input.get("password"));
			driver.findElement(By.id(loginBtn)).click();
	  }
	  
	  
	  public String getScreenshot(String testCaseName, WebDriver driver) {
		  TakesScreenshot sc=(TakesScreenshot)driver;
		  File source=sc.getScreenshotAs(OutputType.FILE);
		  File dest=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		  try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	  }
	 
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@AfterClass
	@After
	public void tearDown() {
		driver.close();
	}
	
}
