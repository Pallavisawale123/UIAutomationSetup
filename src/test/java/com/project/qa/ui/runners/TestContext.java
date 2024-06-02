package com.project.qa.ui.runners;

import com.project.qa.ui.driver.UIDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 5/11/2024
 */
public class TestContext {
    private UIDriverManager driverManager;

    public TestContext() {
        driverManager = new UIDriverManager();
    }

    /**
     * Method to get web driver manager class instance
     *
     * @return web driver manager class instance
     */
    public UIDriverManager getWebDriverManager() {
        return driverManager;
    }


}
