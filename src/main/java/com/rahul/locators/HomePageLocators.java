package com.rahul.locators;

public class HomePageLocators {
	
	public String addToCart=".toast-message";
	//public String successMsg=".toast-title";
	//public String successMsg="//div[contains(@class,'toast-success')]";
	public String successMsg="//div[@id='toast-container']/div/div";
	public String productNames="//h5/b";
	public String addToCartButton="(//button[contains(@class,'w-10')])";
	public String loadingContent=".la-ball-scale-multiple div";
	public String cartButton="//button[@routerlink='/dashboard/cart']";
	public String ordersBtn="//button[@routerlink='/dashboard/myorders']";
	public String checkOutButton="//button[text()='Checkout']";
	public String productNamesInCartPage="//div[@class='cartSection']/h3";
	public String stockStatus=".stockStatus";
	public String priceOfProduct=".item__price";
	public String productNameonOrderPage=".item__title";
	public String selectCountry="//input[@placeholder='Select Country']";
	public String countries=".ta-item";
	public String placeOrderButton="//a[contains(@class,'action__submit')]";
	public String placeOrderId="//h1/following::tr/td/label[@class='ng-star-inserted']";
	
}
