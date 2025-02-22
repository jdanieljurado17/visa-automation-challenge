Feature: This feature smoke tests that the create pet endpoint works appropriately.

  Scenario: Create a new pet successfully.
    Given the user has a pet with the following data:
      | id  | name  | category  | tags       |
      |  1  | Buddy | Dogs      | vaccinated |
    When the user sends a POST request to create the pet
    Then the pet should be created successfully
