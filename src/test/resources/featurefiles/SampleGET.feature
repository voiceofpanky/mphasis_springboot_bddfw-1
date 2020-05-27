Feature: Test a sample GET

  @api
  Scenario: Send a sample GET request
    Given I send a GET request to "https://reqres.in/api/users/"
    Then I should get a status code "200"
    Then I should see the list has the user firstname as "Eve"

  @api
  Scenario: Send a sample GET request & validate the response schema
    Given I send a GET request to "https://reqres.in/api/users"
    Then I should get a status code "200"
    Then I should see response conforms to json schema "jsonSchemas/userList.json"
