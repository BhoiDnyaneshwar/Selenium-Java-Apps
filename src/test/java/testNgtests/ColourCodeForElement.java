package testNgtests;

import java.time.Duration;

import org.apache.commons.imaging.color.ColorConversions;
import org.apache.commons.imaging.color.ColorHsl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ColourCodeForElement {
public static WebDriver driver;
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();		
		driver = new ChromeDriver();
		driver.get(("https://rahulshettyacademy.com/client"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement btnLogin=driver.findElement(By.id("login"));
		String hexClourValue="#96161f";
		
		//you can get hex value from clour picker website
		
		String colourValue=btnLogin.getCssValue("background-color");
		System.out.println(colourValue);
		String hexValue=Color.fromString(colourValue).asHex();
		
		
		System.out.println(hexValue);
		Assert.assertEquals(hexValue, hexClourValue);	
		
		java.awt.Color col=Color.fromString(colourValue).getColor();
		System.out.println(col);
		
		//use the map by key value pair and get the color name manually
	
		
		
		
		
		
	
		}

	}


