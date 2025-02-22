package com.automation.petstore.utils.serviceProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServiceProperty {

    private static ServiceProperty instance;
    private Properties properties;

    private ServiceProperty() {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/test/java/com/automation/petstore/resources/config/services.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load service properties", e);
        }
    }

    public static ServiceProperty getServiceProperties() {
        if (instance == null) {
            instance = new ServiceProperty();
        }
        return instance;
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public String getPetPath() {
        return properties.getProperty("pet.path");
    }

    public String getApiKey() {
        return properties.getProperty("api.key");
    }

}
