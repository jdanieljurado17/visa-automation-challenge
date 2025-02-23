package com.automation.petstore.utils.dataFormatters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ResponseFormatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parses a JSON response into a Map.
     *
     * This method converts the given {@link JsonNode} into a {@code Map<String, Object>},
     * allowing easy access and manipulation of the response data.
     *
     * @param jsonResponse The JSON response to be parsed.
     * @return A map representation of the JSON response.
     * @throws RuntimeException If an error occurs during JSON parsing.
     */
    public static Map<String, Object> parseResponseToMap(JsonNode jsonResponse) {
        try {
            return objectMapper.convertValue(jsonResponse, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}
