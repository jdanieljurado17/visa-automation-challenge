package com.automation.petstore.utils.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class PayloadBuilder {
    /**
     * Builds a Json payload with the passed data for Pet Creation.
     * @param id to use.
     * @param name of the pet.
     * @param category for the pet.
     * @param tags to use.
     * @return Json converted to String.
     */
    public static String buildPetJson(String id, String name, String category, String tags) {
        try {
            Map<String, Object> petData = new HashMap<>();
            petData.put("id", id);
            petData.put("name", name);

            Map<String, Object> categoryData = new HashMap<>();
            categoryData.put("id", 1);
            categoryData.put("name", category);
            petData.put("category", categoryData);

            petData.put("photoUrls", new String[]{"https://example.com/photo.jpg"});

            Map<String, Object> tagData = new HashMap<>();
            tagData.put("id", 0);
            tagData.put("name", tags);
            petData.put("tags", new Object[]{tagData});

            petData.put("status", "available");

            // Convert to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(petData);
        } catch (Exception e) {
            throw new RuntimeException("Error creating JSON payload", e);
        }
    }
}
