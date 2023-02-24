Feature: sign up
  Scenario: I can sign up with valid credentials
     Given I am on the registration page
     When I enter credentials for registration
     And I click Sign up
     Then I should be logged in
