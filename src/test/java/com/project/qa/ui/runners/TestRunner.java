package com.project.qa.ui.runners;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 5/11/2024
 **/

@RunWith(CustomCucumberRunner.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"com.project.qa.ui.stepdefs"}
)
public class TestRunner {
}
