Feature: Validate the user creation endpoint successfully creates and grants access to a new user

  Scenario: Successfully create and log in a user
    Given the user has valid credentials:
      |id | username       |firstName | lastName |         email            | password  | phone  |
      | 1 | johnRambo017   |  John    |   Rambo  | john.rambo017@email.com  | 12345     | 017117 |
    When the user sends a POST request to create the new user
    Then the response should contain the user details
    When the user sends a GET request to the login endpoint
    Then the response should contain a "Logged in user session:" message followed by a session ID
