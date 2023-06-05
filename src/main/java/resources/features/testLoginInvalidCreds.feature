Feature: web login invalid credentials
  Scenario: login with invalid credentials
    Given I am on the login page
    When I enter invalid credentials
    And I click the login button
    Then I should not be logged in
