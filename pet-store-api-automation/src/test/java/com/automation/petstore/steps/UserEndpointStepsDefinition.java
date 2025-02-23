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
        UserEndpointTests.organizeUserData(credentials);
    }

    @When("the user sends a GET request to the login endpoint")
    public void userLogsIn(){
        UserEndpointTests.getUserLogin();
    }

    @Then("the response should contain a {string} message followed by a session ID")
    public void loginMessageValidation(String message){
        UserRequestsAssertions.validateUserLoginResponse(message);
    }

    @When("the user sends a POST request to create the new user")
    public void userRequestNewUserCreation() throws JsonProcessingException {
        UserEndpointTests.createNewUser();
    }

    @Then("the response should contain the user details")
    public void validateUserCreatedResponse(){
        UserRequestsAssertions.validateUserCreationResponse("userJson", "createUserResponse");
    }

    @When("the user sends a PUT request to update the created user")
    public void userUpdatesCreatedUser() throws JsonProcessingException {
        UserEndpointTests.updateUser();
    }
}
