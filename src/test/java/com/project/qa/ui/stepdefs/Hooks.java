package com.project.qa.ui.stepdefs;

import com.project.qa.ui.driver.UIDriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
    * @project UI_Automation_Setup
    * @date 5/11/2024
    * @author psawale
*/



public class Hooks {
    private static WebDriver driver;
    static UIDriverManager  driverManager = new UIDriverManager();

    @BeforeStep
    public static void beforeScenario() {
        driver = driverManager.getDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @After
    public void AfterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            driverManager.captureScreenshot(scenario.getName().toLowerCase().replaceAll(" ", ""));
        }
    }



}