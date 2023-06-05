Feature: Language Switcher
  Scenario: Change language
    Given I am on the home page
    When I click the language switcher button
    Then the language on the page should change
