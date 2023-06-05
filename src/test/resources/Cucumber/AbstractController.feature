
Feature: Retrieving information from Javalin Context

  Scenario: Retrieving the current user
    Given a Javalin Context object
    And a session attribute with the key "SESSION_CURRENT_USER_KEY"
    When the currentUser method is called
    Then the method should return the currently logged-in user

  Scenario: Retrieving the current database
    Given a Javalin Context object
    And a session attribute with the key "DB_KEY"
    When the currentDB method is called
    Then the method should return the currently selected database

  Scenario: Retrieving the current message bundle
    Given a Javalin Context object
    And a session attribute with the key "MODEL_KEY"
    When the currentMessages method is called
    Then the method should retrieve the message bundle from the current model
    And if the message bundle is not found in the current model, it should create a new one using the default language

  Scenario: Retrieving the current model
    Given a Javalin Context object
    And a session attribute with the key "MODEL_KEY"
    When the currentModel method is called
    Then the method should return the current model
