Feature: Test a sample POST

  @api
  Scenario: Send a sample POST operation
    Given I have a payload with a user details
    When I send a POST request to "https://reqres.in/api/users/"
    Then I should get the post status code as "200"

  @api
  Scenario: Send a sample POST operation
    Given I have a complex payload with a user details
    When I send a POST request to "https://reqres.in/api/users/"
    Then I should get the post status code as "201"
	
