Feature: Validate the user update endpoint successfully creates and grants access to a modified user

  Scenario: Successfully create, update and log in a user
    Given the user has valid credentials:
      |id | username        |firstName | lastName |         email             | password  | phone  |
      | 3 | johnConnor300   |  John    |  Connor  | john.connor300@email.com  | 12345     | 017117 |
    When the user sends a POST request to create the new user
    Given the user has valid credentials:
      |id | username        |firstName | lastName |         email       | password  | phone  |
      | 3 | johnConnor300   |  T       |  3000    |    t3000@email.com  | 12345     | 017117 |
    When the user sends a PUT request to update the created user
    Then the response should contain the user details
    When the user sends a GET request to the login endpoint
    Then the response should contain a "Logged in user session:" message followed by a session ID
