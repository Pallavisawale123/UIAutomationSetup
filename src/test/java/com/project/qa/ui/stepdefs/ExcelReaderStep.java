package com.project.qa.ui.stepdefs;

import com.project.qa.ui.driver.UIDriverManager;
import com.project.qa.ui.helpers.AssertHelper;
import com.project.qa.ui.pages.TestFormFillUpWithExcel;
import com.project.qa.ui.readers.ConfigReader;
import com.project.qa.ui.readers.ExcelReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 5/11/2024
 */

@Slf4j
public class ExcelReaderStep {

    ConfigReader configReader = new ConfigReader();
    TestFormFillUpWithExcel testFormFillUpWithExcel = new TestFormFillUpWithExcel();
    private String baseURL = configReader.getProperty("base.url");
    UIDriverManager uiDriverManager = new UIDriverManager();
    private WebDriver driver;
    public ExcelReaderStep() {
        this.driver = uiDriverManager.getDriver();
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


    @When("user fills the form from given sheetname {string} and rownumber {}")
    public void userFillsTheFormFromGivenSheetnameAndRownumber(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException, InterruptedException {
        ExcelReader reader = new ExcelReader();
        List<Map<String, String>> testData =
                reader.getData("src/test/resources/testdata/FormDetailsExcel.xlsx", sheetName);

        String name = testData.get(rowNumber).get("Name");
        String email = testData.get(rowNumber).get("Email");
        String currentAdd = testData.get(rowNumber).get("LocalAdd");
        String permanentAdd = testData.get(rowNumber).get("PermanentAdd");

        testFormFillUpWithExcel.clickFormsLink();
        testFormFillUpWithExcel.fillForm(name, email,currentAdd,permanentAdd);
    }
}
