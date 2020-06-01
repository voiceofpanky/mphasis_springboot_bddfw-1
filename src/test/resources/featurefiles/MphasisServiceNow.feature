Feature: Mphasis Service Now Page

  @web @mphasis
  Scenario: Go to MPhasis Service Now Home Page
    Given I am on the "Service Now" page
    And I login successfully
    Then the page title is "Self Service Portal - Mphasis SP CIO"