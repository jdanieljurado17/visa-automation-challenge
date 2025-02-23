package com.automation.petstore.assertions;

import com.automation.petstore.utils.dataFormatters.JsonUtils;
import com.automation.petstore.utils.dataFormatters.ResponseFormatter;
import net.serenitybdd.core.Serenity;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserRequestsAssertions {

    /**
     * Validates the user login response by checking that the response contains the expected login message,
     * extracts the session ID from the response, and stores it in the Serenity session.
     *
     * @param expectedMessage The expected login message (e.g., "Logged in user session:") that must be present in the response.
     * @throws AssertionError if the response does not contain the expected message or if the session ID is missing.
     */
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

    /**
     * Validates the user creation response by comparing the actual response data to the expected data.
     * Both expected and actual data are retrieved from Serenity session variables and converted into maps for comparison.
     *
     * @param expectedDataVariable The key for the session variable containing the expected user JSON payload.
     * @param responseDataVariable The key for the session variable containing the actual API response.
     * @throws AssertionError if any of the user fields (id, username, firstName, lastName, email, password, phone, userStatus) do not match.
     */
    public static void validateUserCreationResponse(String expectedDataVariable, String responseDataVariable) {
        Map<String, Object> responseData = ResponseFormatter.parseResponseToMap(Serenity.sessionVariableCalled(responseDataVariable));
        Map<String, Object> expectedData = JsonUtils.jsonToMap(Serenity.sessionVariableCalled(expectedDataVariable));

        assertThat(String.valueOf(responseData.get("id")))
                .withFailMessage("Expected id to be %s but got %s", expectedData.get("id"), responseData.get("id"))
                .isEqualTo(String.valueOf(expectedData.get("id")));

        assertThat(responseData.get("username"))
                .withFailMessage("Expected username to be %s but got %s", expectedData.get("username"), responseData.get("username"))
                .isEqualTo(expectedData.get("username"));

        assertThat(responseData.get("firstName"))
                .withFailMessage("Expected firstName to be %s but got %s", expectedData.get("firstName"), responseData.get("firstName"))
                .isEqualTo(expectedData.get("firstName"));

        assertThat(responseData.get("lastName"))
                .withFailMessage("Expected lastName to be %s but got %s", expectedData.get("lastName"), responseData.get("lastName"))
                .isEqualTo(expectedData.get("lastName"));

        assertThat(responseData.get("email"))
                .withFailMessage("Expected email to be %s but got %s", expectedData.get("email"), responseData.get("email"))
                .isEqualTo(expectedData.get("email"));

        assertThat(responseData.get("password"))
                .withFailMessage("Expected password to be %s but got %s", expectedData.get("password"), responseData.get("password"))
                .isEqualTo(expectedData.get("password"));

        assertThat(responseData.get("phone"))
                .withFailMessage("Expected phone to be %s but got %s", expectedData.get("phone"), responseData.get("phone"))
                .isEqualTo(expectedData.get("phone"));

        assertThat(String.valueOf(responseData.get("userStatus")))
                .withFailMessage("Expected userStatus to be %s but got %s", expectedData.get("userStatus"), responseData.get("userStatus"))
                .isEqualTo(String.valueOf(expectedData.get("userStatus")));
    }

}
