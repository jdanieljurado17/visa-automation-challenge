package com.automation.petstore.tests;

import com.automation.petstore.utils.builders.PayloadBuilder;
import com.automation.petstore.utils.requests.PetRequests;
import com.automation.petstore.utils.requests.UserRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;

import java.util.List;
import java.util.Map;

public class UserEndpointTests {

    public static void organizeUserCredentials(DataTable credentials){
        List<Map<String, String>> dataList = credentials.asMaps(String.class, String.class);
        Map<String, String> data = dataList.get(0);
        Serenity.setSessionVariable("userCredentials").to(data);
    }

    public static void getUserLogin() throws JsonProcessingException {
        Map<String, String> credentials = Serenity.sessionVariableCalled("userCredentials");

        String response = UserRequests.getUserLogin(credentials.get("username"), credentials.get("password"));
        Serenity.setSessionVariable("userEndpointResponse").to(response);
    }


}
