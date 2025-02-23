package com.automation.petstore.steps;

import com.automation.petstore.assertions.StoreRequestsAssertions;
import com.automation.petstore.tests.StoreEndpointTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StoreEndpointStepsDefinition {

    @Given("the user wants to create an order with a quantity of {string} for pet ID {string}")
    public void userWantsToCreateAnOrder(String quantity, String petID){
        StoreEndpointTests.organizeExpectedOrderData(quantity, petID);
    }

    @When("the user sends a POST request to place the order")
    public void userPlacesAnOrder() throws JsonProcessingException {
        StoreEndpointTests.createOrderRequest();
    }

    @Then("the response body matches the order data")
    public void orderPlacementResponseValidation(){
        StoreRequestsAssertions.validateStoreEndpointDynamicResponse("orderJson", "storeEndpointResponse");
    }

    @And("the user sends a GET request to retrieve the order by ID")
    public void userSendsGetOrderRequest() throws JsonProcessingException {
        StoreEndpointTests.getOrderRequest();
    }
}
