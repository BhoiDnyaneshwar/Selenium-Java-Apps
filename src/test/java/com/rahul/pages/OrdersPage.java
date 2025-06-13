package com.rahul.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahul.locators.OrdersPageLocators;

public class OrdersPage {
	public WebDriver driver;
	public OrdersPageLocators ordersPagelocator;
	public DriverUtils driverUtil;
	
	public OrdersPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this); 
		this.ordersPagelocator=new OrdersPageLocators();
		this.driverUtil=new DriverUtils(this.driver);
	}
	
	public boolean getOrderIdOfProductPlaced(String productName, String Id) {
		System.out.println(Id);
		boolean match = false;
		List<WebElement> orderId=driver.findElements(By.xpath(ordersPagelocator.txtOrderId.replace("productName", productName)));
		for(WebElement ids:orderId) {
			if(ids.getText().equals(Id)) {
				System.out.println(ids.getText());
				match=true;
				break;
			}
		}
		return match;
	}

}
