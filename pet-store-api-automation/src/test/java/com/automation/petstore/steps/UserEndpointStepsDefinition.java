package com.automation.petstore.steps;

import com.automation.petstore.assertions.UserRequestsAssertions;
import com.automation.petstore.tests.UserEndpointTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserEndpointStepsDefinition {

    @Given("the user has valid credentials:")
    public void userHasValidCredentials(DataTable credentials){
        UserEndpointTests.organizeUserCredentials(credentials);
    }

    @When("the user sends a GET request to the login endpoint")
    public void userLogsIn() throws JsonProcessingException {
        UserEndpointTests.getUserLogin();
    }

    @Then("the response should contain a {string} message followed by a session ID")
    public void loginMessageValidation(String message){
        UserRequestsAssertions.validateUserLoginResponse(message);
    }
}
