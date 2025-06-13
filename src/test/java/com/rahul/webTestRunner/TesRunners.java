package com.rahul.webTestRunner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/java/com/rahul/features/ProductsOrder.feature",  // Make sure the path is correct
	    glue = {"com.rahul.aloneTest"}, // Make sure it points to your step definition package
	    plugin = {"pretty", "html:target/cucumber-reports.html"},
	    tags="getClient",
	    monochrome = true
)
public class TesRunners extends AbstractTestNGCucumberTests {
	
}
