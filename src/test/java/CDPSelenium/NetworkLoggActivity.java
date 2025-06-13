package CDPSelenium;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.network.Network;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkLoggActivity {
	
	//Tracking Network logging activity for req/res if any request is failed then we can track
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		DevTools devTool=driver.getDevTools();
		devTool.createSession();
		
		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTool.addListener(Network.requestWillBeSent(), request->{
			System.out.println(request.getRequest().getUrl());
		});
		
		devTool.addListener(Network.responseReceived(), response->{
			System.out.println(response.getResponse().getUrl());
			System.out.println(response.getResponse().getStatus());
			if(response.getResponse().getStatus().toString().contains("4")) {
				System.out.println(response.getResponse().getUrl()+" is failed with status code "+response.getResponse().getStatus());
			}
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo");
	
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

	}

}
