package com.project.qa.ui.runners;

import cucumber.api.junit.Cucumber;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;


/**
 * @project UI_Automation_Setup
 * @date 5/11/2024
 * @author psawale
 */

/**
 * This class overrides default test listener with custom one
 */
public class CustomCucumberRunner extends Cucumber {

    public CustomCucumberRunner(Class clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    public void run(RunNotifier runNotifier) {
        runNotifier.addListener(new JUnitListeners());
        runNotifier.addListener(new RetryAnalyzer());
        super.run(runNotifier);
    }
}
