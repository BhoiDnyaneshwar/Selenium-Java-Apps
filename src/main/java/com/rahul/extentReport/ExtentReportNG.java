package com.rahul.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	static ExtentReports reports;
	
	public static ExtentReports generateReport() {
		String path=System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Automation Result");
		reporter.config().setReportName("WebAutomation RahulShetty");
		reports=new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Dnyaneshwar");
		return reports;
	}

}
