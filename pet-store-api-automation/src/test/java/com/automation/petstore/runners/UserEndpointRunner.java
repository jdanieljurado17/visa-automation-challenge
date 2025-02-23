package com.automation.petstore.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/automation/petstore/resources/features/smoke/userEndpointFeatures",
        glue = "com.automation.petstore.steps",
        plugin = {"pretty", "json:target/storeEndpoint-cucumber.json"},
        monochrome = true
)
public class UserEndpointRunner extends AbstractTestNGCucumberTests {
}