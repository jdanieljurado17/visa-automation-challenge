Feature: This feature smoke tests that the delete pet by ID endpoint works appropriately.

  Scenario: Successfully create and delete a pet by ID
    Given the user has a pet with the following data:
      | id  | name  | category  | tags       |
      |  4  | Mango | Cats      | vaccinated |
    When the user sends a POST request to create the pet
    And the user sends a DELETE request to delete the pet by ID
    Then the response should contain the "Pet deleted" message
