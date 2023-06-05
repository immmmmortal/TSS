Feature: Change subscription plan functionality
  Scenario: User changes subscription plan successfully
    Given I am loged in to my account
    And I am on profile page
    And I select a different subscription plan
    Then I should see a notification that my plan has been changed