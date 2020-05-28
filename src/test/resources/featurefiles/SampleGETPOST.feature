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
    Then I should get a confirmation of the addition - force fail

