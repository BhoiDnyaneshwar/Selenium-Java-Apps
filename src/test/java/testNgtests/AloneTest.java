package testNgtests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rahul.locators.HomePageLocators;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AloneTest {
public static WebDriver driver;
public HomePageLocators loginPagelocator=new HomePageLocators();

	public AloneTest() {
		
	}
	

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		AloneTest at=new AloneTest();
		at.loginPage();
	}
	

	public void loginPage() throws InterruptedException {
		//WebElement emailBox=driver.findElement(By.id(loginPagelocator.userEmailTxtBox));
		//WebElement passBox=driver.findElement(By.id(loginPagelocator.userPasswordTxtBox));
		String deleteSearchCriteria=Keys.chord(Keys.CONTROL,"A",Keys.DELETE);
		
		//emailBox.sendKeys(deleteSearchCriteria);
		Thread.sleep(3000);
		//emailBox.sendKeys("dnyanumore97+rahulshetty@gmail.com");
		//passBox.sendKeys(deleteSearchCriteria,"Dnyanu@123");
		//driver.findElement(By.id(loginPagelocator.loginBtn)).click();
	}
	

}
