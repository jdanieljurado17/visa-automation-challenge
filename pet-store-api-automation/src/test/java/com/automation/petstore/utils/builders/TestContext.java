package com.automation.petstore.utils.builders;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private static Map<String, Object> context = new HashMap<>();

    public static void set(String key, Object value) {
        context.put(key, value);
    }

    public static Object get(String key) {
        return context.get(key);
    }
}
