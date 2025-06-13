package CDPSelenium;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.fetch.Fetch;
import org.openqa.selenium.devtools.v135.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v135.network.model.ErrorReason;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchFailedRequest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		DevTools devTool=driver.getDevTools();
		devTool.createSession();
		
		//If the client want to failed request explicitly and know the result on UI then that time it work
		
		Optional<List<RequestPattern>>	patterns=Optional.of(Arrays.asList(new RequestPattern(Optional.of("*Get*"), Optional.empty(), Optional.empty())));
		
		devTool.send(Fetch.enable(patterns, Optional.empty()));
		
		devTool.addListener(Fetch.requestPaused(), reqPaused->{
			devTool.send(Fetch.failRequest(reqPaused.getRequestId(), ErrorReason.FAILED));
		});

		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
	}

}
