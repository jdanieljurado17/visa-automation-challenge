package com.automation.petstore.tests;

import com.automation.petstore.utils.builders.PayloadBuilder;
import com.automation.petstore.utils.requests.PetRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;

import java.util.List;
import java.util.Map;


public class PetEndpointTests {

    /**
     * Sends a request to create a pet using the provided payload.
     *
     * @param payLoad The JSON payload containing pet details.
     * @return The response from the service request as a {@link JsonNode}.
     * @throws JsonProcessingException If an error occurs while processing the JSON.
     */
    public static JsonNode sendPetCreationRequest(String payLoad) throws JsonProcessingException {
        return  PetRequests.createPetRequest(payLoad);
    }

    /**
     * Organizes and stores the expected pet data from a {@link DataTable}.
     *
     * This method extracts the first row of the DataTable, converts it into a map,
     * and sets the expected data as a session variable. Additionally, it builds
     * the JSON payload for the pet creation request and stores it as a session variable.
     *
     * @param petData A {@link DataTable} containing the pet's expected attributes.
     */
    public static void organizeExpectedPetData(DataTable petData){
        List<Map<String, String>> dataList = petData.asMaps(String.class, String.class);
        Map<String, String> data = dataList.get(0);
        Serenity.setSessionVariable("expectedData").to(data);
        String petJson = PayloadBuilder.buildPetJson(data.get("id"), data.get("name"), data.get("category"), data.get("tags"));
        Serenity.setSessionVariable("petJson").to(petJson);
    }

    /**
     * Sends a pet creation request and stores the response as a global session variable.
     *
     * This method retrieves the JSON payload stored in the session, sends the request,
     * and saves the API response in a session variable for further validation.
     *
     * @throws JsonProcessingException If an error occurs while processing the JSON.
     */
    public static void createPetAndSetGlobalVariables() throws JsonProcessingException {
        JsonNode response = sendPetCreationRequest(Serenity.sessionVariableCalled("petJson"));
        Serenity.setSessionVariable("createPetResponse").to(response);
    }

    /**
     * Sends a GET request to retrieve pet details by ID.
     *
     * This method retrieves the expected pet data stored in Serenity session variables,
     * extracts the pet ID, and calls the `getPetByIDRequest` method to fetch the pet details.
     * The response is then stored in the Serenity session for further validation.
     *
     * @throws JsonProcessingException if an error occurs while processing the JSON response.
     */
    public static void getPetByIdRequest() throws JsonProcessingException {
        Map<String, String> expectedData = Serenity.sessionVariableCalled("expectedData");
        JsonNode response = PetRequests.getPetByIDRequest(expectedData.get("id"));
        Serenity.setSessionVariable("petEndpointResponse").to(response);
    }

    /**
     * Sends a request to update an existing pet and stores the response in a Serenity session variable.
     *
     * @throws JsonProcessingException if there's an error processing the JSON response.
     */
    public static void updatePetRequest() throws JsonProcessingException {
        JsonNode response = PetRequests.updatePetRequest(Serenity.sessionVariableCalled("petJson"));
        Serenity.setSessionVariable("petEndpointResponse").to(response);
    }

    /**
     * Deletes a pet using the ID stored in the Serenity session.
     * Stores the delete response in the Serenity session.
     */
    public static void deletePetRequest(){
        Map<String, String> expectedData = Serenity.sessionVariableCalled("expectedData");
        String response = PetRequests.deletePetByIDRequest(expectedData.get("id"));
        Serenity.setSessionVariable("deletePetResponse").to(response);
    }

    public static void updatePetByForm() throws JsonProcessingException {
        Map<String, String> expectedData = Serenity.sessionVariableCalled("expectedData");

        JsonNode response = PetRequests.updatePetByFormRequest(expectedData.get("id"), expectedData.get("name"), expectedData.get("status"));
        Serenity.setSessionVariable("petEndpointResponse").to(response);
    }
}
