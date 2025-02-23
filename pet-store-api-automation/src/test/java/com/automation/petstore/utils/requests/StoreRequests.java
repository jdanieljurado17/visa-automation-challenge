package com.automation.petstore.utils.requests;

import com.automation.petstore.utils.serviceProperties.ServiceProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreRequests {

    /**
     * Sends a POST request to create an order and returns the API response.
     *
     * @param orderPayLoad The JSON payload for the order.
     * @return The API response as a JsonNode.
     * @throws JsonProcessingException If there is an error processing the JSON response.
     */
    public static JsonNode postOrderRequest(String orderPayLoad) throws JsonProcessingException {
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String storeOrderPath = serviceProperty.getStoreOrderPath();
        String apiKey = serviceProperty.getApiKey();

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .header("api_key", apiKey)
                .contentType(ContentType.JSON)
                .body(orderPayLoad)
                .post(storeOrderPath);

        response.then().assertThat().statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.asString());
    }

    /**
     * Sends a GET request to fetch an order by its pet ID from the store order endpoint.
     *
     * @param petId The ID of the pet associated with the order.
     * @return The response as a JsonNode object containing the order details.
     * @throws JsonProcessingException if there is an error parsing the response JSON.
     */
    public static JsonNode getOrderByIDRequest(String petId) throws JsonProcessingException {
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String storeOrderPath = serviceProperty.getStoreOrderPath();
        String apiKey = serviceProperty.getApiKey();

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .header("api_key", apiKey)
                .contentType(ContentType.JSON)
                .get(storeOrderPath +"/" + petId );

        response.then().assertThat().statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.asString());
    }
}
