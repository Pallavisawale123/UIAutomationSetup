package com.project.qa.ui.pages;

import com.project.qa.ui.driver.UIDriverManager;
import com.project.qa.ui.helpers.AssertHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 6/2/2024
 */
@Slf4j
public class TestFormFillUpWithExcel {

    WebDriver driver;
    UIDriverManager uiDriverManager = new UIDriverManager();
    AssertHelper assertHelper = new AssertHelper();

    private By Sname = By.id("userName");
    private By email = By.id("userEmail");
    private By localAdd = By.id("currentAddress");
    private By permanent_Add = By.id("permanentAddress");

    public TestFormFillUpWithExcel() {
        this.driver = uiDriverManager.getDriver();
    }

    /**
     * Method to click on text box form
     */
    public void clickFormsLink() {
        driver.findElement(By.xpath("//*[text()='Text Box']")).click();
    }

    public void fillForm(String name, String emailId, String localADD, String permanentAdd) throws InterruptedException {

        driver.findElement(Sname).sendKeys(name);
        Thread.sleep(20);
        driver.findElement(email).sendKeys(emailId);
        Thread.sleep(20);
        driver.findElement(localAdd).sendKeys(localADD);
        Thread.sleep(20);
        driver.findElement(permanent_Add).sendKeys(permanentAdd);
        Thread.sleep(20);

    }

}
