package testNgtests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.rahul.pages.HomePage;
import com.rahul.pages.MS_Verification;
import com.rahul.pages.PropertiesData;

public class SubmitOrderTest {
	MS_Verification ms=new MS_Verification();
	
	@Test
	public void submitOrderForProduct() {
		ms.launchingUrl();
		// login into app
		ms.MSVerificationLogin();
		 
		HomePage homePage = new HomePage(ms.driver);
		
		// login msg
		Assert.assertEquals(homePage.getMessage(), "Login Successfully");
		
		//Add product to cart
		PropertiesData propData=new PropertiesData();
		homePage.addToCart(propData.getProperties("Product"));
		
		//go to cart and Verify product in stock
		homePage.clickOnButton("Cart");
		homePage.verifyProductInStock("IN STOCK");
		homePage.clickOnButton("Checkout");
		
		//fill shipping address
		homePage.shippingAddress();
		
		//placeOrder
		homePage.clickOnButton("Place Order");
		
		//verify Thanks msg
		Assert.assertEquals(homePage.getThankYouMessage(), "THANKYOU FOR THE ORDER.");
	    ms.tearDown();

	    System.out.println("Order submitted successfully.");
	    		System.out.println("WebHooks Testing");
		
	    System.out.println("Order submitted successfully and verified.");
		System.out.println("Trying WebHooks ngrocks");

	}

}
