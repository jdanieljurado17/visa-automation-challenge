package com.automation.petstore.tests;

import com.automation.petstore.utils.builders.PayloadBuilder;
import com.automation.petstore.utils.requests.UserRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;


import java.util.List;
import java.util.Map;

public class UserEndpointTests {

    /**
     * Organizes user data from a Cucumber DataTable and stores it in the Serenity session.
     * The first row of the DataTable is extracted as a key-value map representing user details.
     *
     * @param userData The DataTable containing user information with keys as column headers.
     */
    public static void organizeUserData(DataTable userData){
        List<Map<String, String>> dataList = userData.asMaps(String.class, String.class);
        Map<String, String> userDetails = dataList.get(0);
        Serenity.setSessionVariable("userData").to(userDetails);
    }

    /**
     * Retrieves stored user credentials from the Serenity session and performs a login request.
     * The response is then stored in the session for further validation.
     */
    public static void getUserLogin(){
        Map<String, String> credentials = Serenity.sessionVariableCalled("userData");

        String response = UserRequests.getUserLogin(credentials.get("username"), credentials.get("password"));
        Serenity.setSessionVariable("userEndpointResponse").to(response);
    }

    /**
     * Constructs a JSON payload for a new user using stored session data,
     * sends a user creation request, and stores the response in the Serenity session.
     *
     * @throws JsonProcessingException if an error occurs while constructing the JSON payload.
     */
    public static void createNewUser() throws JsonProcessingException {
        String payload = setUserData();

        JsonNode response = UserRequests.createNewUserRequest(payload);
        Serenity.setSessionVariable("createUserResponse").to(response);
    }

    /**
     * Updates the user using session-stored data.
     *
     * @throws JsonProcessingException If there is an issue processing the JSON response.
     */
    public static void updateUser() throws JsonProcessingException {
        Map<String, String> userData = Serenity.sessionVariableCalled("userData");

        String payload = setUserData();

        JsonNode response = UserRequests.updateUserRequest(userData.get("username"), payload);
        Serenity.setSessionVariable("createUserResponse").to(response);
    }

    /**
     * Builds a user JSON payload using stored session data.
     *
     * @return A JSON string representing the user details.
     */
    public static String setUserData(){
        Map<String, String> userData = Serenity.sessionVariableCalled("userData");

        String payload = PayloadBuilder.buildUserJson(
                userData.get("id"),
                userData.get("username"),
                userData.get("firstName"),
                userData.get("lastName"),
                userData.get("email"),
                userData.get("password"),
                userData.get("phone"));
        Serenity.setSessionVariable("userJson").to(payload);
        return payload;
    }
}
