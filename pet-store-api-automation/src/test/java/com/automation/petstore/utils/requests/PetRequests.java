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

    /**
     * Sends a GET request to retrieve pet details by ID.
     *
     * @param petId The ID of the pet to retrieve.
     * @return A JsonNode containing the pet's details.
     * @throws JsonProcessingException If an error occurs while processing the JSON response.
     */
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

    /**
     * Sends a PUT request to update an existing pet in the Pet Store API.
     *
     * @param petPayLoad The JSON payload containing the pet's updated data.
     * @return A JsonNode representing the API response.
     * @throws JsonProcessingException if there's an error processing the JSON response.
     */
    public static JsonNode updatePetRequest(String petPayLoad) throws JsonProcessingException {
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String petPath = serviceProperty.getPetPath();
        String apiKey = serviceProperty.getApiKey();

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .header("api_key", apiKey)
                .contentType(ContentType.JSON)
                .body(petPayLoad)
                .put(petPath);

        response.then().assertThat().statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.asString());
    }

    /**
     * Sends a DELETE request to remove a pet by ID.
     *
     * @param petId The ID of the pet to delete.
     * @return The response body as a String.
     */
    public static String deletePetByIDRequest(String petId) {
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String petPath = serviceProperty.getPetPath();
        String apiKey = serviceProperty.getApiKey();

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .header("api_key", apiKey)
                .contentType(ContentType.JSON)
                .when()
                .delete(petPath + "/" + petId);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        return response.asString();
    }

    public static JsonNode updatePetByFormRequest(String petId, String petName, String petStatus) throws JsonProcessingException {
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String petPath = serviceProperty.getPetPath();
        String apiKey = serviceProperty.getApiKey();

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .header("api_key", apiKey)
                .contentType(ContentType.JSON)
                .queryParam("name", petName)
                .queryParam("status", petStatus)
                .post(petPath + "/" + petId);

        response.then().assertThat().statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.asString());
    }
}
