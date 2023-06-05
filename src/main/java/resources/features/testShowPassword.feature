Feature: Show Password Functionality
  Scenario: Show and hide password
    Given User is on the login page
    When I click the show password button
    Then I should be able to see the password
    And I should be able to hide the password
