package CDPSelenium;

import java.sql.Connection;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.network.model.ConnectionType;
import org.openqa.selenium.devtools.v135.fetch.Fetch;
import org.openqa.selenium.devtools.v135.network.Network;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EmulateNetworkSpeed {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		DevTools devTool=driver.getDevTools();
		devTool.createSession();
		
		//When onshore team is disgined code and pushed to github that time there is no traffic
		//Offshore team working start then site response is slowly and taking time to perform action that scenario we can do here
		
		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTool.send(Network.emulateNetworkConditions(false, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET), Optional.empty(), Optional.empty(), Optional.of(false)));

		devTool.addListener(Network.loadingFailed(), loadingFailed->{
			System.out.println(loadingFailed.getErrorText());
			System.out.println(loadingFailed.getTimestamp());
			
			
		});
		
		long startTime = System.currentTimeMillis();
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		driver.close();

	}

}
