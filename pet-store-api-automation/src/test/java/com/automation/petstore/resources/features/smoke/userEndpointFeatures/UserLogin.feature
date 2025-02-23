Feature: Validate the user login endpoint successfully grants access

  Scenario: Successfully log in a user
    Given the user has valid credentials:
      | username       | password  |
      | john@email.com | 12345     |
    When the user sends a GET request to the login endpoint
    Then the response should contain a "Logged in user session:" message followed by a session ID
