package com.listner;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rahul.extentReport.ExtentReportNG;
import com.rahul.pages.MS_Verification;

public class ListnerNG extends MS_Verification implements ITestListener  {
	public MS_Verification msv= new MS_Verification(); // Using the driver from MS_Verification class
	ExtentReports reports=ExtentReportNG.generateReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> testThread=new ThreadLocal<ExtentTest>();
	
	@Override
    public void onTestStart(ITestResult result) {
		test=reports.createTest(result.getMethod().getMethodName());
		testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	testThread.get().log(Status.PASS, "Passed");
        System.out.println("Test Passed: " + result.getName());
		/*
		 * try {
		 * driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get
		 * (result.getInstance()); } catch (IllegalArgumentException |
		 * IllegalAccessException | NoSuchFieldException | SecurityException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
        
        String filepath=getScreenshot(result.getMethod().getMethodName(), driver);
        testThread.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	testThread.get().fail(result.getThrowable());
		
		  try {
		  driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get
		  (result.getInstance()); 
		  String filepath=getScreenshot(result.getMethod().getMethodName(), driver);
	        testThread.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		  } catch (IllegalArgumentException |
		  IllegalAccessException | NoSuchFieldException | SecurityException e) { 
			  //TODO Auto-generated catch block 
			  //e.printStackTrace(); 
			  }
		 
        
		  
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        reports.flush();
    }

}
