package com.rahul.pages;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.testng.annotations.DataProvider;


public class DataProviderNG {
	JSONParsing jsp=new JSONParsing();	
	
	@DataProvider(name="loginCred")
	public Object[][] provideData() {
		/*
		 * HashMap<String, String> h1=new HashMap<String, String>();
		 *  h1.put("email",
		 * "sp@gmail.com"); h1.put("password", "1223");
		 *  HashMap<String, String> h2=new
		 * HashMap<String, String>(); h2.put("email", "cp@gmail.com");
		 * h2.put("password", "22222");
		 */
		
		List<HashMap<String, String>> data=jsp.getJsonDataToHashMap(".\\\\Config\\\\userData.json");
	   return new Object[][] {{data.get(0)},{data.get(1)}};

}
}