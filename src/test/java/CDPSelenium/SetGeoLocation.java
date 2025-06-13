package CDPSelenium;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.emulation.Emulation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetGeoLocation {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		DevTools devTool=driver.getDevTools();		
		devTool.createSession();
		
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("latitude", 40);
		params.put("longitude", 3);
		params.put("accuracy", 1);
		driver.executeCdpCommand("Emulation.setGeolocationOverride", params);
				
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//textArea[@title='Search']")).sendKeys("netFlix", Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".LC20lb ")).click();
		String txt=driver.findElement(By.tagName("h1")).getText();
		System.out.println(txt);
	}
}
