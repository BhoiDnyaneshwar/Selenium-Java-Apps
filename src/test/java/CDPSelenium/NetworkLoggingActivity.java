package CDPSelenium;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.network.model.Response;
import org.openqa.selenium.devtools.v135.network.Network;


import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkLoggingActivity {

	public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver=new ChromeDriver();
	DevTools devTool=driver.getDevTools();
	devTool.createSession();
	
	devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()) );
	devTool.addListener(Network.responseReceived(), response ->{
		Response res=response.getResponse();
		System.out.println(res.getUrl());
		System.out.println(res.getStatus());
	});

	
	driver.get("https://rahulshettyacademy.com/angularAppdemo");
	driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
	}

}
