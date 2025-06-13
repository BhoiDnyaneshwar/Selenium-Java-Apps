package com.rahul.aloneTest;

import com.rahul.DBUtils.DbPage;
import com.rahul.DBUtils.Db_Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DatabaseStepDefinitions {
	public Db_Utils util=new Db_Utils(); 
	public DbPage dbPage=new DbPage(); 

	@Given("I Connected to the DB {string}")
    public void i_connected_to_the_db(String dbName) {
        dbPage.getConnection(dbName);
        System.out.println("Connected to the DB: " + dbName);
    }

    @When("I execute {string} Query for table {string}")
    public void i_execute_query_for_table(String queryType, String tableName) {
       dbPage.getExecuteQuery(queryType, tableName);
        System.out.println("Executing " + queryType + " query for table: " + tableName);
    }

    @Then("I Should Extracted the {string} for {string} - {string}")
    public void i_should_extracted_the_for(String fieldName, String identifierType, String identifierValue) {
    	util.getValueBasedOnCondn(dbPage.storedResultsetAsList, fieldName, identifierType, identifierValue);
        System.out.println("Extracted " + fieldName + " for " + identifierType + " - " + identifierValue);
    }
}
