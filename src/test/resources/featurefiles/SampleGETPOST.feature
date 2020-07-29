Feature: Test a sample API Endpoint

  @api
  Scenario: Check my firstname exits at the list of users
    Given I send a request to get the list of users
    When I have the list of users with me
    Then I should see the list has the user firstname as "Eve"

  @api
  Scenario: Validate the response schema matches for list of users
    Given I send a request to get the list of users
    When I have the list of users with me
    Then I should see response conforms to json schema "jsonSchemas/userList.json"

  @api
  Scenario: Add a user to the list of users
    Given I have a payload with a user details
    When I send a request to add the user to the list
    Then I should get a confirmation of the addition

  @api
  Scenario: Add another user to the list of users
    Given I have a complex payload with a user details
    When I send a request to add the user to the list
    Then I should get a confirmation of the addition
    
  @api1
  Scenario: Confirm user with id=23 does not exist
    Given I have a userid "23"
    When I send a request to fetch user details
    Then I should not be able to fetch user details - force fail
    
  @api
  Scenario: Register user with email id "sampleemail@gmail.com"
    Given I have a user with email id "sampleemail@gmail.com"
    When I send a request to register the user 
    Then I should not be able to register - force fail
  

