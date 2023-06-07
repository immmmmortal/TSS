Scenario: Show Profile
Given a user is logged in
When the user navigates to the profile page
Then the profile page should be displayed

Scenario: Change language
Given a user is logged in
When the user selects a language
Then the language of the user should be updated

Scenario: Reset database password
Given a user is logged in
When the user resets the database password
Then the database password of the user should be updated

Scenario: Reset web password
Given a user is logged in
When the user resets the web password
Then the web password of the user should be updated

Scenario: Reset API password
Given a user is logged in
When the user resets the API password
Then the API password of the user should be updated

Scenario: Upgrade user role
Given a user is logged in
When the user upgrades the user role
Then the user role of the user should be updated

Scenario: Remove self
Given a user is logged in
When the user removes their account
Then the account of the user should be deleted

Scenario: Confirm account removal
Given a user is logged in
When the user requests to remove their account
Then a confirmation message should be displayed