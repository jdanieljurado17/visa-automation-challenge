package com.automation.petstore.utils.requests;

import com.automation.petstore.utils.serviceProperties.ServiceProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserRequests {

    public static String getUserLogin(String username, String password) throws JsonProcessingException {
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
}
