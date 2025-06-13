package CDPSelenium;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.page.Page;

import com.rahul.pages.DriverUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PageScreenshot {
	static DriverUtils driverUtil;

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		DevTools devTool=driver.getDevTools();
		devTool.createSession();
		
		driverUtil=new DriverUtils(driver);
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/angularAppdemo");
    	
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
		driverUtil.waitForPageLoad();
		driverUtil.waitForVisibilityOfElement(By.xpath("//td[text()='LSA']"));
		
		devTool.send(Page.enable(Optional.empty()));
		String base64Image=devTool.send(Page.captureScreenshot(Optional.empty(), Optional.of(100), Optional.empty(), Optional.of(true), Optional.of(false), Optional.of(false)));
		
		 // Save to file
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        // Folder inside project path
        String folderPath = System.getProperty("user.dir") + File.separator + "screenshots";
        new File(folderPath).mkdirs();  // Create folder if it doesn't exist

        // File name with timestamp
        String filePath = folderPath + File.separator + "screenshot_" + System.currentTimeMillis() + ".png";

        // Write image to file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(imageBytes);
        }

        System.out.println("Screenshot saved at: " + filePath);
       	}

}
