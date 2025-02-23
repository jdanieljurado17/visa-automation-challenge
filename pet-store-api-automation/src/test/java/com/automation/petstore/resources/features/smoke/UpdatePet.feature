Feature: This feature smoke tests that the get pet by ID endpoint works appropriately.

  Scenario: Successfully create a pet and retrieve it by ID
    Given the user has a pet with the following data:
      | id  | name   | category  | tags       |
      |  3  | Peluso | Cats      | vaccinated |
    When the user sends a POST request to create the pet
    And the user sends a PUT request to update the pet with the following data:
      | id  | name   | category  | tags       |
      |  3  | Coco   | Cats      | vaccinated |
    Then the response should contain the pet data matching the created pet
