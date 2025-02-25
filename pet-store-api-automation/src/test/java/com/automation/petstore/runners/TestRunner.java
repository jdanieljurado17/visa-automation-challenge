package com.automation.petstore.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = "src/test/java/com/automation/petstore/resources/features",
        glue = "com.automation.petstore.steps",
        plugin = {"pretty", "json:target/cucumber.json"},
        monochrome = true
)
@Listeners({AllureTestNg.class})
public class TestRunner extends AbstractTestNGCucumberTests {
}
