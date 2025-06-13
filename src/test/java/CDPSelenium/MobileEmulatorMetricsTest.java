package CDPSelenium;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.emulation.Emulation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileEmulatorMetricsTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		DevTools devTool=driver.getDevTools();		
		devTool.createSession();
		//devTool.send(Emulation.setDevi)
		devTool.send(Emulation.setDeviceMetricsOverride(400, 600, 97, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("a[routerlink*='products']")).click();
		
		driver.close();
		
	}
}
