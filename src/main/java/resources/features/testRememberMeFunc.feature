Feature: "remember me" checkbox functionality
  Scenario: "remember me" checkbox is checked
    Given User on the login page
    When I enter valid credentials with remember me checked and click login
    Then User should be logged in

