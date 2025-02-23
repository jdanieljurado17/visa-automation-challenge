package com.automation.petstore.utils.builders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(petData);
        } catch (Exception e) {
            throw new RuntimeException("Error creating JSON payload", e);
        }
    }

    /**
     * Builds a JSON payload for an order.
     *
     * @param id       The order ID.
     * @param quantity The quantity of items in the order.
     * @return A JSON string representing the order.
     */
    public static String buildOrderJson(String id, String quantity) {
        try {
            Map<String, Object> orderData = new HashMap<>();
            orderData.put("id", id);

            int petId = new Random().nextInt(999999) + 1;
            orderData.put("petId", petId);
            orderData.put("quantity", quantity);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

            String shipDate = ZonedDateTime.now(ZoneOffset.UTC)
                    .truncatedTo(ChronoUnit.MILLIS)
                    .format(formatter);
            orderData.put("shipDate", shipDate);
            orderData.put("status", "approved");
            orderData.put("complete", true);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(orderData);
        } catch (Exception e) {
            throw new RuntimeException("Error creating JSON payload", e);
        }
    }

}
