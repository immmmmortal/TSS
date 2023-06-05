
Feature: Backup and Restore API

  Background:
    Given a valid public key and signature

  Scenario: Create a backup
    When the user creates a backup with database name "example" and point name "backup1"
    Then the API should return a status code of 204

  Scenario: Restore from backup
    When the user restores from backup with database name "example" and point name "backup1"
    Then the API should return a status code of 204
