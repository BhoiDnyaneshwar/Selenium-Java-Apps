package com.rahul.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import javax.swing.text.Highlighter.Highlight;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
public WebDriver driver;
public WebDriverWait wait;

public DriverUtils(WebDriver driver) {
	this.driver=driver;
	
}

public void sleep(int sec) {
	try {
		int milisec=sec*1000;
		Thread.sleep(milisec);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
public void waitForVisibilityOfElement(By locator) {
	wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}

public void waitForInvisibilityOfElement(By locator) {
	wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
}

public void waitByJavaScript(By locator) {
	((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", driver.findElement(locator));
}

public void waitForWebElement(By locator) {
	Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(30))
			.pollingEvery(Duration.ofMillis(5))
			.ignoring(NoSuchElementException.class);
	
	wait.until(new Function<WebDriver, Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			
			return driver.findElement(locator).isDisplayed();
		}
	});
}

public void waitForPageLoad() {
	wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	wait.until(new Function<WebDriver,Boolean>(){

		@Override
		public Boolean apply(WebDriver driver) {
			JavascriptExecutor js=(JavascriptExecutor) driver;
			return js.executeScript("return document.readyState").equals("complete");
		}
		
	});
}

public void selectItemByVisibleText(WebElement ele, String value) {
	Select select=new Select(ele);
	List<WebElement> options=select.getOptions();
	for(WebElement option:options) {
		if(option.equals(value)) {
			select.selectByVisibleText(value);
			
		}
	}
}

public void scrollToElementToBeClickable(WebElement ele) {
	JavascriptExecutor js=(JavascriptExecutor) driver;
	js.executeScript("document.body.scrollIntoView();",ele);
	
}

public void elementToBeClick(WebElement ele) {
	JavascriptExecutor js=(JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();",ele);
	
}

}
