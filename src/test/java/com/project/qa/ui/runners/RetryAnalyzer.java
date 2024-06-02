package com.project.qa.ui.runners;

import org.junit.runner.notification.RunListener;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 5/12/2024
 */
public class RetryAnalyzer extends RunListener implements IRetryAnalyzer {
    private int count = 0;
    private static final int MAX_RETRY_COUNT = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (count < MAX_RETRY_COUNT) {
                count++;
                return true;
            }
        }
        return false;
    }
}