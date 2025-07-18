package com.listner;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int count=0;
	int maxTry=1;

	@Override
	public boolean retry(ITestResult result) {
		// run test case 1 time
		if(count<maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
