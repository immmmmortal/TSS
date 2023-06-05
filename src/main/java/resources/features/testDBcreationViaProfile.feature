Feature: Create Database
  Scenario: Create a new database
    Given I am logged in to my account
    When I create a new database on my profile page
    Then database should be listed in my account info