Feature: Validate the create order endpoint can successfully place an order and retrieve it by ID

  Scenario: Successfully create an order and retrieve it by ID
    Given the user wants to create an order with a quantity of "2" for pet ID "2"
    When the user sends a POST request to place the order
    And the user sends a GET request to retrieve the order by ID
    Then the response body matches the order data
