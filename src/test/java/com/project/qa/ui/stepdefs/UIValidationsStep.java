package com.project.qa.ui.stepdefs;

import com.project.qa.ui.driver.UIDriverManager;
import com.project.qa.ui.helpers.AssertHelper;
import com.project.qa.ui.readers.ConfigReader;
import cucumber.api.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 5/11/2024
 */

@Slf4j
public class UIValidationsStep {

    ConfigReader configReader = new ConfigReader();

    private String baseURL = configReader.getProperty("base.url");

    private WebDriver driver;

    public UIValidationsStep() {
        this.driver = Hooks.getDriver();
    }

    @Given("get the launch url")
    public void launchBaseUrl() {
        driver.get(baseURL);
        AssertHelper.assertTrue(driver.getTitle() != null, "URL Launch" + driver.getTitle());
    }

    @Given("close the launch url")
    public void closeTheLaunchUrl() {
        driver.close();
    }
}
