package com.rahul.pages;

import java.util.List;

import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.Highlighter.HighlightPainter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahul.locators.HomePageLocators;

public class HomePage {

	public WebDriver driver;
	public HomePageLocators homePagelocator;
	public DriverUtils driverUtil;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		this.driverUtil=new DriverUtils(this.driver);
		this.homePagelocator=new HomePageLocators();
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(tagName = "h1")
	WebElement thankYouMsg;
	
	
	public String getMessage() {
		//driverUtil.waitForVisibilityOfElement(By.xpath(homePagelocator.successMsg));
		driverUtil.waitForWebElement(By.xpath(homePagelocator.successMsg));
		//String msg= driver.findElement(By.xpath(homePagelocator.successMsg)).getText().trim();
		String msg= driver.findElement(By.xpath(homePagelocator.successMsg)).getAttribute("aria-label");		
		return msg;
	}
	
	public String getOrderID() {
		driverUtil.waitForVisibilityOfElement(By.xpath(homePagelocator.placeOrderId));
		//System.out.println(driver.findElement(By.xpath(homePagelocator.placeOrderId)).getText());
		String id=driver.findElement(By.xpath(homePagelocator.placeOrderId)).getText();
		String ID=id.split(" ")[1].trim();
		return ID;
	}
	
	public String getThankYouMessage() {
		String msg= thankYouMsg.getText();		
		return msg;
	}
	
	public void addToCart(String product) {
		driverUtil.sleep(3);
		List<WebElement> ele=driver.findElements(By.xpath(homePagelocator.productNames));
		int prodIndex=0;
		for(int i=0;i<ele.size();i++) {
			if(ele.get(i).getText().contains(product)) {
				prodIndex=i+1;
				break;
			}
		}
		WebElement productToAddCart=driver.findElement(By.xpath(homePagelocator.addToCartButton.concat("["+prodIndex+"]")));
		//driverUtil.scrollToElementToBeClickable(productToAddCart);
		//productToAddCart.click();
		driverUtil.elementToBeClick(productToAddCart);
		driverUtil.waitForInvisibilityOfElement(By.cssSelector(homePagelocator.loadingContent));
		
	}
	
	public void clickOnButton(String buttonName) {
		String ele="";
		switch(buttonName) {
		case "Cart": ele=homePagelocator.cartButton;
		break;
		case "Checkout": ele=homePagelocator.checkOutButton;
		break;
		case "Place Order": ele=homePagelocator.placeOrderButton;
		break;
		case "ORDERS": ele=homePagelocator.ordersBtn;
		break;
		default: System.out.println("Invalid Input");
		}
		driverUtil.elementToBeClick(driver.findElement(By.xpath(ele)));
		//driver.findElement(By.xpath(ele)).click();
	}
	
	public String verifyProductInStock(String product) {
		String status="";
		driverUtil.waitForVisibilityOfElement(By.xpath(homePagelocator.productNamesInCartPage));
		String productName=driver.findElement(By.xpath(homePagelocator.productNamesInCartPage)).getText();
		if(productName.contains(product)) {
		 status=driver.findElement(By.cssSelector(homePagelocator.stockStatus)).getText();
		}
		return status.trim();
	}
	
	public String verifyPriceOfProduct(String product) {
		String price="";
		driverUtil.waitForVisibilityOfElement(By.cssSelector(homePagelocator.productNameonOrderPage));
		String productName=driver.findElement(By.cssSelector(homePagelocator.productNameonOrderPage)).getText();
		if(productName.contains(product)) {
			price=driver.findElement(By.cssSelector(homePagelocator.priceOfProduct)).getText();
		}
		return price.trim();
	}
	
	public void shippingAddress() {
		WebElement country=driver.findElement(By.xpath(homePagelocator.selectCountry));
		country.click();
		country.sendKeys("India");
		driverUtil.waitForVisibilityOfElement(By.cssSelector(homePagelocator.countries));
		List<WebElement> countries=driver.findElements(By.cssSelector(homePagelocator.countries));
		for(WebElement ele:countries) {
			if(ele.getText().trim().equals("India")) {
				ele.click();
			}
		}
	}
}
