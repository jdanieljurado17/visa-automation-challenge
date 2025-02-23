package com.automation.petstore.tests;

import com.automation.petstore.utils.builders.PayloadBuilder;
import com.automation.petstore.utils.builders.TestContext;
import com.automation.petstore.utils.requests.StoreRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.json.Json;


public class StoreEndpointTests {

    /**
     * Organizes expected order data and stores it in the test context.
     *
     * @param quantity The quantity of items in the order.
     * @param petId    The ID of the pet associated with the order.
     */
    public static void organizeExpectedOrderData(String quantity, String petId){
        TestContext.set("quantity", quantity);
        TestContext.set("petId", petId);

        String orderJson = PayloadBuilder.buildOrderJson(String.valueOf(TestContext.get("quantity")), String.valueOf(TestContext.get("petId")));
        Serenity.setSessionVariable("orderJson").to(orderJson);
    }

    /**
     * Sends a request to create an order and stores the response.
     *
     * @throws JsonProcessingException If there is an error processing the JSON response.
     */
    public static void createOrderRequest() throws JsonProcessingException {
        JsonNode response = StoreRequests.postOrderRequest(Serenity.sessionVariableCalled("orderJson"));
        Serenity.setSessionVariable("storeEndpointResponse").to(response);
    }

    /**
     * Sends a request to retrieve an order by pet ID and stores the response in a Serenity session variable.
     *
     * @throws JsonProcessingException if there is an error parsing the response to JSON.
     */
    public static void getOrderRequest() throws JsonProcessingException {

        JsonNode response = StoreRequests.getOrderByIDRequest(String.valueOf(TestContext.get("petId")));
        Serenity.setSessionVariable("storeEndpointResponse").to(response);
    }

}
