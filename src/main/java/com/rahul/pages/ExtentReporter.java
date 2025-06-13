package com.rahul.pages;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	ExtentSparkReporter reporter;
	ExtentReports reports;
	
	public void generateReports() {
		String path=System.getProperty("user.dir")+".//test-output//Reportsdex.html";
		reporter=new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("RahulShettyProducts");
		reporter.config().setReportName("Test Reports");
		
		reports.attachReporter(reporter);
		reports.setSystemInfo("TesterName-", "Dnyaneshwar Bhoi");
		reports.flush();
	}

}
