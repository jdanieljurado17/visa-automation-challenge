package com.automation.petstore.utils.requests;

import com.automation.petstore.utils.serviceProperties.ServiceProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserRequests {

    /**
     * Sends a login request with provided credentials.
     *
     * @param username User's username
     * @param password User's password
     * @return The login response as a string
     */
    public static String getUserLogin(String username, String password){
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String userLoginPath = serviceProperty.getUserLoginPath();
        String apiKey = serviceProperty.getApiKey();

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .header("api_key", apiKey)
                .contentType(ContentType.JSON)
                .queryParam("username", username)
                .queryParam("password", password)
                .get(userLoginPath);

        response.then().assertThat().statusCode(200);

        return response.asString();
    }

    /**
     * Sends a request to create a new user.
     *
     * @param userPayLoad JSON payload containing user details
     * @return Response as a JsonNode
     * @throws JsonProcessingException If JSON processing fails
     */
    public static JsonNode createNewUserRequest(String userPayLoad) throws JsonProcessingException {
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String userPath = serviceProperty.getUserPath();

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userPayLoad)
                .post(userPath);

        response.then().assertThat().statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.asString());
    }

    /**
     * Sends a PUT request to update an existing user in the system.
     *
     * @param username   The username of the user to be updated.
     * @param petPayLoad The JSON payload containing the updated user details.
     * @return A JsonNode representing the response body.
     * @throws JsonProcessingException If there is an issue processing the JSON response.
     */
    public static JsonNode updateUserRequest(String username, String petPayLoad) throws JsonProcessingException {
        ServiceProperty serviceProperty = ServiceProperty.getServiceProperties();
        String baseUrl = serviceProperty.getBaseUrl();
        String userPath = serviceProperty.getUserPath() + "/" + username;

        RestAssured.baseURI = baseUrl;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petPayLoad)
                .put(userPath);

        response.then().assertThat().statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.asString());
    }
}
