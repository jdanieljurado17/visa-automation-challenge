Feature: This feature smoke tests that the update pet endpoint works appropriately.

  Scenario: Successfully create and update a pet
    Given the user has a pet with the following data:
      | id  | name   | category  | tags       |
      |  3  | Peluso | Cats      | vaccinated |
    When the user sends a POST request to create the pet
    And the user sends a POST request to update the pet with the form data endpoint:
      | id  | name   | status     |
      |  3  | Coco   | available  |
    Then the response should contain the updated pet data
