Feature: Verify Database Functionality

@getClient
  Scenario: Extract username and passowrd by using clientNumber
    Given I Connected to the DB "demo.Database"
    When I execute "SELECT" Query for table "dbo.gb_pbo"
    Then  I Should Extracted the "benefitCode" for "policyNumber" - "1234567"