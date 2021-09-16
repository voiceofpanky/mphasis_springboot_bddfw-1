Feature: Validate Payment screen and Make a payment

  Background: User Navigation to Account Summary page
    Given User logged into TestBank
    And User lands on Dashboard page

  @native @nativeApp @ios
  Scenario: User Navigation to Payments page
    When User clicks on "Payment"
    Then User lands on Payments page

  @native @nativeApp @ios
  Scenario Outline: User makes a payment to a contact
    And User clicks on "Payment"
    And User lands on Payments page
    And User selects "<Country>"
    And User enters "<phonenumber>" "<Name>" "<Amount>"
    When User clicks on SendPayment
    Then User able to make payment Successfully

    Examples:
      | phonenumber | Name        | Amount | Country |
      | 51299827277 | Tamilselvan | 50     | India   |
      | 77712277727 | Test        | 15     | India   |
