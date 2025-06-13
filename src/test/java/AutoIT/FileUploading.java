package AutoIT;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.rahul.pages.DriverUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUploading {
	public static WebDriver driver;
	public static DriverUtils driverUtil;
	public static void main(String[] args) throws IOException {
		
		String downloadPath=System.getProperty("user.dir");
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.ilovepdf.com/jpg_to_pdf");
		driver.findElement(By.xpath("//span[contains(text(),'Select JPG images')]")).click();
		
		driverUtil =new DriverUtils(driver);
		driverUtil.sleep(2);
		
		JFileChooser jf=new JFileChooser();
		jf.showOpenDialog(null);
		File file=jf.getSelectedFile();
		FileInputStream fis=new FileInputStream(file);
		
		JOptionPane.showMessageDialog(null, "File Successfully Selected!");
		//we can directly pass file path
		//   "C:\\Users\\Dnyaneshwar\\OneDrive\\Pictures\\Smart Game Booster\\FileUpload.exe"
		
		Runtime.getRuntime().exec(file.getAbsolutePath());
		
		
		driverUtil.waitForVisibilityOfElement(By.id("processTaskTextBtn"));
		
		driver.findElement(By.id("processTaskTextBtn")).click();
		
        driverUtil.waitForVisibilityOfElement(By.id("pickfiles"));		
		driver.findElement(By.id("pickfiles")).click();
		
		driverUtil.sleep(5);
		
		File f=new File(downloadPath+"\\image_2.pdf");
		
		if(f.exists()) {
			Assert.assertTrue(f.exists());
			if(f.delete()) {
				System.out.println("File Gone Deleted");
			}
		}
		
		driver.quit();
	}
}
