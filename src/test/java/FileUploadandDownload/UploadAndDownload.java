
package FileUploadandDownload;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.rahul.pages.DriverUtils;

import ExcelUtils.ExcelUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadAndDownload {
	public static WebDriver driver;
	public static DriverUtils driverUtil;
	
	
	public static void main(String[] args) {
        String downloadPath=System.getProperty("user.dir");
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		 WebDriverManager.chromedriver().setup();	
		  driver=new ChromeDriver(options);		 
		  driver.manage().window().maximize();
		  driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		  driverUtil =new DriverUtils(driver);
		  
		  //download file
		  driverUtil.waitForVisibilityOfElement(By.xpath("//button[text()='Download']"));
		  driver.findElement(By.xpath("//button[text()='Download']")).click();
		  driverUtil.sleep(3);
		  
		  //edit the excel file
		  ExcelUtility excelUtils=new ExcelUtility();
		  excelUtils.setCellUpdatedValue(downloadPath+"//download.xlsx", "Sheet1", "price", "Apple", "500");
		  
		  //choose file-When Type is -'file' then we can use sendKeys method to upload file- without using AUTO IT
		  WebElement uploadBtn=driver.findElement(By.xpath("//input[@type='file']"));
		  uploadBtn.sendKeys(downloadPath+"//download.xlsx");
		  WebElement uploadMsg= driver.findElement(By.cssSelector("div[class='Toastify__toast-body'] div:nth-child(2)"));
		  System.out.println(uploadMsg.getText());
		  driverUtil.waitForInvisibilityOfElement(By.cssSelector("div[class='Toastify__toast-body'] div:nth-child(2)"));
		  
		  //excel utils to getValue
		  System.out.println(excelUtils.getExpectedCellValue(downloadPath+"//download.xlsx", "Sheet1", "price", "Apple"));
		  
	}

}
