package com.automation.petstore.utils.requests;

import com.automation.petstore.utils.serviceProperties.ServiceProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PetRequests {


    /**
     * Sends a request to create a pet using the provided payload.
     *
     * This method constructs and sends an HTTP POST request to the pet creation endpoint,
     * including the necessary headers and request body. The response is then validated
     * to ensure a 200 status code before being parsed into a {@link JsonNode}.
     *
     * @param petPayLoad The JSON payload containing the pet details.
     * @return The API response as a {@link JsonNode}.
     * @throws JsonProcessingException If an error occurs while processing the response JSON.
     */
    public static JsonNode createPetRequest(String petPayLoad) throws JsonProcessingException {
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String petPath = serviceProperty.getPetPath();
        String apiKey = serviceProperty.getApiKey();

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .header("api_key", apiKey)
                .contentType(ContentType.JSON)
                .body(petPayLoad)
                .post(petPath);

        response.then().assertThat().statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.asString());
    }


    public static JsonNode getPetByIDRequest(String petId) throws JsonProcessingException {
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String petPath = serviceProperty.getPetPath();
        String apiKey = serviceProperty.getApiKey();

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .header("api_key", apiKey)
                .contentType(ContentType.JSON)
                .get(petPath +"/" + petId );

        response.then().assertThat().statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.asString());
    }

}
