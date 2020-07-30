Feature: Reading File


  Scenario: Login to WebAppSecurity Page
    Given User on the webAppSecurity  login page
    When User entered <"username"> and <"password">
    Then User Navigated to account summary page