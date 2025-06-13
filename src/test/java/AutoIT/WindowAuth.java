package AutoIT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowAuth {
	//if we have window Auth where we have to enter username and password then lading to the page 
	//then we have pass it by URl so whenever it ask for window auth then URL can handle it.
	
	public static WebDriver driver;
	
	  public static void main(String[] args) {
		  WebDriverManager.chromedriver().setup();	
		  driver=new ChromeDriver();		 
			driver.manage().window().maximize();
			driver.get("https://admin:admin@the-internet.herokuapp.com/");
			driver.findElement(By.cssSelector("a[href*='basic_auth']")).click();
			
			String msg=driver.findElement(By.xpath("//h3/./following-sibling::p")).getText();
			System.out.println(msg);
			Assert.assertEquals(msg, "Congratulations! You must have the proper credentials.");
			driver.quit();
			
	}

}
