
Given the user is authenticated and has logged in
When the user accesses the home page
Then the home page is displayed with the following information:
- A list of available databases associated with the user's account
- The amount of storage space used and total available storage space
- The percentage of storage space used
- The database host and port information


Given the user is not authenticated or has not logged in
When the user tries to access the home page
Then the user is redirected to the login page

