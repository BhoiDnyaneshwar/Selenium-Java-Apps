package CDPSelenium;

import java.util.Arrays;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.network.Network;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetBlockedURI {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		DevTools devTool=driver.getDevTools();
		devTool.createSession();
		
		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTool.send(Network.setBlockedURLs(Arrays.asList("*.css","*.jpg")));
		
	long	startTime=	System.currentTimeMillis();
	driver.get("https://rahulshettyacademy.com/angularAppdemo/");
	driver.findElement(By.linkText("Browse Products")).click();
	driver.findElement(By.linkText("Selenium")).click();
	driver.findElement(By.cssSelector(".add-to-cart")).click();
	System.out.println(driver.findElement(By.cssSelector("p")).getText());
	long endTime = System.currentTimeMillis();
	System.out.println(endTime - startTime);


	}

}
