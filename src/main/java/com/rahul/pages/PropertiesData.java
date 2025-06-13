package com.rahul.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesData {
	 public static Properties prop;
	 FileOutputStream fileOut;
	 
	 
	    public String getProperties(String key){
		    prop=new Properties();
			try {
				prop.load(new FileInputStream(".\\src\\test\\resources\\OrderIds.properties"));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			String value=prop.getProperty(key);	
			return value;
		}
		
		public void setProperties(String productName, String orderId) {
			prop=new Properties();
			try {
				prop.load(new FileInputStream(".\\src\\test\\resources\\OrderIds.properties"));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			try {
			 fileOut=new FileOutputStream(new File(".\\\\src\\\\test\\\\resources\\\\OrderIds.properties"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			prop.setProperty(productName, orderId);
			
			try {
				prop.store(fileOut, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
