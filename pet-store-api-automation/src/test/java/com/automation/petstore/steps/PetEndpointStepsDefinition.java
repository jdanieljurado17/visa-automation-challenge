package com.automation.petstore.steps;

import com.automation.petstore.assertions.PetRequestsAssertions;
import com.automation.petstore.tests.PetEndpointTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;


public class PetEndpointStepsDefinition {

    @Given("the user has a pet with the following data:")
    public void theUserHasPetData(DataTable petData){
        PetEndpointTests.organizeExpectedPetData(petData);
    }

    @When("the user sends a POST request to create the pet")
    public void theUserCreatesAPet() throws JsonProcessingException {
        PetEndpointTests.createPetAndSetGlobalVariables();
    }

    @Then("the pet should be created successfully")
    public void petCreatedValidation(){
        PetRequestsAssertions.validateCreatePetResponse();
    }

    @And("the user sends a GET request to retrieve the pet by ID")
    public void theUserRetrieveAPetByID() throws JsonProcessingException {
        PetEndpointTests.getPetByIdRequest();
    }

    @Then("the response should contain the pet data matching the created pet")
    public void  retrievedPetDataValidation(){
        PetRequestsAssertions.validatePetEndpointDynamicResponse("expectedData", "getByIdResponse");
    }

}
