Feature: Login functionality

  Scenario: User logs in with valid credentials
    Given the user navigates to the login page
    When the user enters valid credentials and submits the form
    Then the user should be redirected to the home page

  Scenario: User logs in with invalid credentials
    Given the user navigates to the login page
    When the user enters invalid credentials and submits the form
    Then the user should see an error message and remain on the login page

  Scenario: User resets their password
    Given the user navigates to the password reset page
    When the user enters their email and submits the form
    Then the user should see a success message and receive a password reset email
    And the user should be redirected to the login page
    Given the user clicks on the password reset link in the email
    When the user enters a new password and submits the form
    Then the user should see a success message and be redirected to the login page

Feature: User authentication and authorization

  Scenario: Unauthenticated user tries to access a protected page
    Given the user is not authenticated
    When the user tries to access a protected page
    Then the user should be redirected to the login page

  Scenario: Authenticated user tries to access a protected page
    Given the user is authenticated
    When the user tries to access a protected page they are authorized to view
    Then the user should see the protected page

  Scenario: Authenticated user tries to access a protected page they are not authorized to view
    Given the user is authenticated
    When the user tries to access a protected page they are not authorized to view
    Then the user should see an error message and be redirected to the home page