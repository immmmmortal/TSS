
Feature: User registration

  Scenario: User visits registration page
    Given the user is not logged in
    When the user navigates to the registration page
    Then the registration page is displayed

  Scenario: User submits registration form with valid information
    Given the user is not logged in
    When the user submits valid registration information
    Then a new user account is created and an activation link is sent to the user's email

  Scenario: User submits registration form with invalid email
    Given the user is not logged in
    When the user submits invalid email during registration
    Then an error message is displayed and the registration page is displayed again

  Scenario: User submits registration form with invalid password
    Given the user is not logged in
    When the user submits invalid password during registration
    Then an error message is displayed and the registration page is displayed again

  Scenario: User confirms email address
    Given the user is not logged in
    When the user clicks on the activation link in their email
    Then their account is activated and they are redirected to the home page

  Scenario: User requests to resend email confirmation
    Given the user is logged in
    When the user requests to resend the activation link
    Then a new activation link is sent to the user's email and a message is displayed