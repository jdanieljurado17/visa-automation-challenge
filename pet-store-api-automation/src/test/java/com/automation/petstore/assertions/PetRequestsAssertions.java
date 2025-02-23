package com.automation.petstore.assertions;

import com.automation.petstore.utils.dataFormatters.ResponseFormatter;
import net.serenitybdd.core.Serenity;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class PetRequestsAssertions {

    /**
     * Validates the response from Create Pet Request
     */
    public static void validateCreatePetResponse(){
        Map<String, Object> responseData = ResponseFormatter.parseResponseToMap(Serenity.sessionVariableCalled("createPetResponse"));
        Map<String, String> expectedData = Serenity.sessionVariableCalled("expectedData");


        assertThat(responseData.get("name"))
                .withFailMessage("Expected pet name to be %s but got %s", expectedData.get("name"), responseData.get("name"))
                .isEqualTo(expectedData.get("name"));

        assertThat(((Map<String, Object>) responseData.get("category")).get("name"))
                .withFailMessage("Expected pet category to be %s but got %s", expectedData.get("category"), responseData.get("category"))
                .isEqualTo(expectedData.get("category"));

        assertThat(((Map<String, Object>) ((List<Object>) responseData.get("tags")).get(0)).get("name"))
                .withFailMessage("Expected pet category to be %s but got %s", expectedData.get("tags"), responseData.get("tags"))
                .isEqualTo(expectedData.get("tags"));
    }

    /**
     * Validates the pet data returned from an API response against the expected pet data.
     *
     * This method extracts expected pet data and response data from Serenity session variables,
     * converts the response JSON into a Map, and performs assertions to ensure the pet's
     * name, category, and tags match the expected values.
     *
     *
     * @param expectedDataVariable The Serenity session variable key where the expected pet data is stored.
     * @param responseDataVariable The Serenity session variable key where the API response data is stored.
     * @throws ClassCastException if the response format does not match the expected structure.
     */
    public static void validatePetEndpointDynamicResponse(String expectedDataVariable, String responseDataVariable){
        Map<String, Object> responseData = ResponseFormatter.parseResponseToMap(Serenity.sessionVariableCalled(responseDataVariable));
        Map<String, String> expectedData = Serenity.sessionVariableCalled(expectedDataVariable);


        assertThat(responseData.get("name"))
                .withFailMessage("Expected pet name to be %s but got %s", expectedData.get("name"), responseData.get("name"))
                .isEqualTo(expectedData.get("name"));

        assertThat(((Map<String, Object>) responseData.get("category")).get("name"))
                .withFailMessage("Expected pet category to be %s but got %s", expectedData.get("category"), responseData.get("category"))
                .isEqualTo(expectedData.get("category"));

        assertThat(((Map<String, Object>) ((List<Object>) responseData.get("tags")).get(0)).get("name"))
                .withFailMessage("Expected pet category to be %s but got %s", expectedData.get("tags"), responseData.get("tags"))
                .isEqualTo(expectedData.get("tags"));
    }

    /**
     * Validates that the delete pet response matches the expected message.
     *
     * @param expectedMessage The expected response message.
     * @throws AssertionError If the actual response does not match the expected message.
     */
    public static void validateDeletePetResponse(String expectedMessage){
        String response = Serenity.sessionVariableCalled("deletePetResponse");

        assertThat(response)
                .withFailMessage("Expected response to be '%s' but got: '%s'", expectedMessage, response)
                .isEqualTo(expectedMessage);
    }
}
