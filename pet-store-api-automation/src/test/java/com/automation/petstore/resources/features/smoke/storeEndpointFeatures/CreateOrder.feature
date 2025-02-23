Feature: Validate the create order endpoint can successfully place an order

  Scenario: Successfully create an order request
    Given the user wants to create an order with a quantity of "2" for pet ID "1"
    When the user sends a POST request to place the order
    Then the response body matches the order data
