package com.automation.petstore.assertions;

import com.automation.petstore.utils.dataFormatters.JsonUtils;
import com.automation.petstore.utils.dataFormatters.ResponseFormatter;
import net.serenitybdd.core.Serenity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreRequestsAssertions {

    /**
     * Validates the dynamic response from the store endpoint against expected data.
     *
     * @param expectedDataVariable The session variable containing expected order data.
     * @param responseDataVariable The session variable containing the API response data.
     */
    public static void validateStoreEndpointDynamicResponse(String expectedDataVariable, String responseDataVariable){
        Map<String, Object> responseData = ResponseFormatter.parseResponseToMap(Serenity.sessionVariableCalled(responseDataVariable));
        Map<String, Object> expectedData =  JsonUtils.jsonToMap(Serenity.sessionVariableCalled(expectedDataVariable));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX");

        String expectedDate = ZonedDateTime.parse(expectedData.get("shipDate").toString())
                .withZoneSameInstant(ZoneOffset.UTC)
                .format(dateFormatter);

        String responseDate = ZonedDateTime.parse(responseData.get("shipDate").toString())
                .withZoneSameInstant(ZoneOffset.UTC)
                .format(dateFormatter);

        assertThat(String.valueOf(responseData.get("id")))
                .withFailMessage("Expected id to be %s but got %s", expectedData.get("id"), responseData.get("id"))
                .isEqualTo(String.valueOf(expectedData.get("id")));

        assertThat(String.valueOf(responseData.get("petId")))
                .withFailMessage("Expected pet id to be %s but got %s", expectedData.get("petId"), responseData.get("petId"))
                .isEqualTo(String.valueOf(expectedData.get("petId")));

        assertThat(String.valueOf(responseData.get("quantity")))
                .withFailMessage("Expected quantity to be %s but got %s", expectedData.get("quantity"), responseData.get("quantity"))
                .isEqualTo(String.valueOf(expectedData.get("quantity")));


        assertThat(responseDate)
                .withFailMessage("Expected ship Date to be %s but got %s", expectedDate, responseDate)
                .isEqualTo(expectedDate);
    }
}
