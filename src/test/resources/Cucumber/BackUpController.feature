Feature: Backup management

  Background:
    Given I am logged in as a user with "admin" role

  Scenario: Create backup
    Given I am on the backup page for "database1"
    When I create a backup named "mybackup"
    Then I should see a success message
    And I should see the new backup in the list

  Scenario: Restore backup
    Given I am on the backup page for "database1"
    And I have a backup named "mybackup"
    When I restore the backup
    Then I should see a success message

  Scenario: Delete backup
    Given I am on the backup page for "database1"
    And I have a backup named "mybackup"
    When I delete the backup
    Then I should see a success message
    And the backup should not be in the list

