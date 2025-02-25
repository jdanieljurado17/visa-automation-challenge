package com.automation.petstore.utils.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AllureConfig implements ITestListener {

    private static final String ENVIRONMENT_PROPERTIES = "allure-results/environment.properties";
    private static final String CATEGORIES_JSON = "allure-results/categories.json";

    @Override
    public void onStart(ITestContext context) {
        setupEnvironment();
        setupCategories();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveTextLog("Test failed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        saveTextLog("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        saveTextLog("Test skipped: " + result.getMethod().getMethodName());
    }

    @Attachment(value = "Execution Logs", type = "text/plain")
    private String saveTextLog(String message) {
        return message;
    }

    private void setupEnvironment() {
        try {
            Properties properties = new Properties();
            properties.setProperty("OS", System.getProperty("os.name"));
            properties.setProperty("Java Version", System.getProperty("java.version"));
            properties.setProperty("Tester", "John Doe"); // Customize as needed
            properties.setProperty("Environment", "Staging"); // Change based on config
            properties.store(new FileOutputStream(ENVIRONMENT_PROPERTIES), "Environment Details");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupCategories() {
        String categoriesJson = "[\n" +
                "  {\n" +
                "    \"name\": \"Flaky tests\",\n" +
                "    \"matchedStatuses\": [\"failed\"],\n" +
                "    \"flaky\": true\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Critical issues\",\n" +
                "    \"matchedStatuses\": [\"failed\"],\n" +
                "    \"messageRegex\": \".*Critical.*\"\n" +
                "  }\n" +
                "]";
        try {
            Files.write(Paths.get(CATEGORIES_JSON), categoriesJson.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
