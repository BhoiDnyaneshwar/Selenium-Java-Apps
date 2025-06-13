package com.rahul.aloneTest;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.configuration.ConfigurationXMLReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.rahul.pages.DataProviderNG;
import com.rahul.pages.HomePage;
import com.rahul.pages.MS_Verification;
import com.rahul.pages.OrdersPage;
import com.rahul.pages.ProductPojo;
import com.rahul.pages.PropertiesData;
import com.rahul.pages.XMLParsing;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PlaceOrderProductStepDef{
public static  WebDriver driver;
public HomePage homePage;	
public XMLParsing xmlParsing;
public ProductPojo productPojo;
public MS_Verification msVerify;
public OrdersPage ordersPage;
public PropertiesData propData;

public PlaceOrderProductStepDef() {
	this.driver=MS_Verification.driver;
	this.homePage=new HomePage(driver);
	this.xmlParsing=new XMLParsing();
	this.productPojo=new ProductPojo();	
	this.ordersPage=new OrdersPage(driver);
	this.msVerify=new MS_Verification();
	this.propData=new PropertiesData();
}	
	
	
	@Given("Im Login as {string}")
	public void im_login_as_svc_specialist(String role) throws InterruptedException {
		if(xmlParsing.configXMLReader("testAccount","role").equals(role)) {
			msVerify.MSVerificationLogin();
		}
	}

	@When("I add  {string} to add to cart")
	public void i_add_to_add_to_cart(String product) {
		productPojo.setProduct(product);
		homePage.addToCart(product);
	}

	@Then("I should see message {string}")
	public void i_should_see_message(String msg) {
	   System.out.println(homePage.getMessage());
	   Assert.assertTrue(homePage.getMessage().trim().equals(msg), " :Is Dispalyed");
	}
	
	@Then("I should see success message saying {string}")
	public void i_should_see_uccess_message_saying (String msg) {
		propData.setProperties(productPojo.getProduct(), homePage.getOrderID());
		System.out.println(homePage.getOrderID());
		System.out.println(homePage.getThankYouMessage());
		Assert.assertTrue(homePage.getThankYouMessage().trim().equals(msg), " :Is Dispalyed");
	}

	@When("I click on {string} button")
	public void i_click_on_cart_button(String buttonName) {
		homePage.clickOnButton(buttonName);
	}
	
	@Then("I should see {string} is Displaying with It's OrderId which is Placed")
	public void  i_should_see_is_Displaying_with_Its_OrderId_which_is_Placed(String productName) {
		Assert.assertTrue(ordersPage.getOrderIdOfProductPlaced(productName, propData.getProperties(productName)));
	}

	@When("Verify {string} {string}")
	public void verify_in_stock(String product, String status) {
		
		 Assert.assertTrue(homePage.verifyProductInStock(product).equals(status), " : In Stock");
	}

	@When("I {string} The {string} of  {string}")
	public void i_checkout_the_of(String buttonName, String price, String product) {
		homePage.clickOnButton(buttonName);
		Assert.assertTrue(homePage.verifyPriceOfProduct(product).equals(price), " : Price");
	}

	@When("I Filled The Shipping Address")
	public void i_filled_the_shipping_address() {
		homePage.shippingAddress();
	}

	@When("Place The Order")
	public void place_the_order() {
	    
	}


}
