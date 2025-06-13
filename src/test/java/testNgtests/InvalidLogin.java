package testNgtests;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.listner.Retry;
import com.rahul.pages.DataProviderNG;
import com.rahul.pages.HomePage;
import com.rahul.pages.MS_Verification;

public class InvalidLogin  {
	MS_Verification ms=new MS_Verification();
	
	@Test(dataProvider = "loginCred", dataProviderClass = DataProviderNG.class, retryAnalyzer = Retry.class)
	public void invalidLoginUsers(HashMap<String,String> input) {
		ms.launchingUrl();
		ms.invalidUserLogin(input);
		HomePage homePage=new HomePage(ms.driver);
		System.out.println(homePage.getMessage());	
		ms.tearDown();
	}

}
