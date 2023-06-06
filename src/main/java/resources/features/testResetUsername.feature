Feature: Reset Username
  As a user
  I want to reset my username in the system
  So that I can use a new username for my account

  Scenario: Resetting the username
    Given the user is logged in account
    When the user resets their username
    Then the database username should be updated
    And the user should be redirected to the basic page
    And the message should be displayed

