Feature: Transfer of Fund from own account

  @web @fundTransfer
  Scenario: Login to my bank account overview page
    Given I am on bank homepage
    When I login to my bank account successfully
    Then I am on account overview page

  @web @fundTransfer
  Scenario Outline: Transfer Fund from my checking to savings account
    Given I am logged in to my bank account overview page
    And I click on "Transfer Funds"
    When I enter "<Amount>"
    And select From "<From Account>" and To "<To Account>"
    And click on Transfer
    Then I should see Transfer Confirmation page

    Examples: 
      | Amount | From Account | To Account |
      |     50 |        21780 |      21891 |
