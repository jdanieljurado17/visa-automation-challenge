package com.automation.petstore.assertions;

import com.automation.petstore.utils.requests.ResponseFormatter;
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
}
