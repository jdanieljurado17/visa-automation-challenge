package com.automation.petstore.steps;

import com.automation.petstore.assertions.PetRequestsAssertions;
import com.automation.petstore.tests.CreatePetTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;


public class CreatePetStepsDefinition {

    @Given("the user has a pet with the following data:")
    public void theUserHasPetData(DataTable petData){
        CreatePetTests.organizeExpectedPetData(petData);
    }

    @When("the user sends a POST request to create the pet")
    public void theUserCreatesAPet() throws JsonProcessingException {
        CreatePetTests.createPetAndSetGlobalVariables();
    }

    @Then("the pet should be created successfully")
    public void petCreatedValidation(){
        PetRequestsAssertions.validateCreatePetResponse();
    }

}
