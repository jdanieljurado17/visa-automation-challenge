package com.automation.petstore.assertions;

import net.serenitybdd.core.Serenity;

public class UserRequestsAssertions {
    public static void validateUserLoginResponse(String expectedMessage) {
            String response = Serenity.sessionVariableCalled("userEndpointResponse");

        // Check if response contains the expected text
        if (!response.contains(expectedMessage)) {
            throw new AssertionError("Response does not contain expected login message.");
        }

        // Extract session ID using regex
        String sessionId = response.replace("Logged in user session: ", "").trim();

        if (sessionId.isEmpty()) {
            throw new AssertionError("Session ID is missing in the response.");
        }

        Serenity.setSessionVariable("userSessionId").to(sessionId);
    }

}
