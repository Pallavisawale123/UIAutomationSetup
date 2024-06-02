package com.project.qa.ui.driver;

import com.project.qa.ui.enums.DriverType;
import com.project.qa.ui.enums.EnvironmentType;
import com.project.qa.ui.readers.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 5/11/2024
 */
@Component
@Slf4j
public class UIDriverManager {
    private WebDriver driver;
    ConfigReader configReader = new ConfigReader();
    private DriverType driverType = DriverType.valueOf(configReader.getProperty("ui.driver.type"));

    private int implicitWait = Integer.parseInt(configReader.getProperty("ui.driver.implicit-wait"));

    private EnvironmentType env = EnvironmentType.valueOf(configReader.getProperty("ui.driver.env"));

    private String screenshotPath = configReader.getProperty("ui.driver.screenshot.path");

    private boolean headlessEnabled = Boolean.parseBoolean(configReader.getProperty("ui.driver.headless"));

    private String hubURL =configReader.getProperty("ui.driver.hub");

    EventListener eventListener = new EventListener();

    /**
     * Method to get web driver instance
     *
     * @return web driver
     */
    public WebDriver getDriver(){
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver(){
        switch (env) {
            case REMOTE:
                driver = createRemoteDriver();
                break;
            case LOCAL:
                driver = createLocalDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver(){
        switch (driverType) {
            case CHROME:
                //WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--diable--notifications");
                chromeOptions.addArguments("force-device-scale-factor=0.80");
                String downloadDir = "C:\\Installations\\temp\\pro-automation\\target";
                HashMap prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", downloadDir); // Bypass default download directory in Chrome
                prefs.put("safebrowsing.enabled", "false");
                chromeOptions.setExperimentalOption("prefs", prefs);
//                if(headlessEnabled){
//                    chromeOptions.setHeadless(true);
//                    chromeOptions.addArguments("window-size=1400,2100");
//                }

                try {
                    driver = new RemoteWebDriver(new URL(hubURL), chromeOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                break;
            case FIREFOX:

                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait)); //default 0 seconds, 0.5 seconds

        EventFiringDecorator eventFiringDecorator = new EventFiringDecorator(eventListener);

        driver = eventFiringDecorator.decorate(driver);

        return driver;
    }

    private WebDriver createLocalDriver(){

        switch (driverType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--diable--notifications");
                chromeOptions.addArguments("force-device-scale-factor=0.80");
                String downloadDir = "C:\\Installations\\temp\\";
                HashMap prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", downloadDir); // Bypass default download directory in Chrome
                prefs.put("safebrowsing.enabled", "false");
                chromeOptions.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait)); //default 0 seconds, 0.5 seconds

        EventFiringDecorator eventFiringDecorator = new EventFiringDecorator(eventListener);

        driver = eventFiringDecorator.decorate(driver);

        return driver;
    }

    public void closeBrowser(){
        driver.close();
        driver.quit();
        driver = null;
    }

    /**
     * Method to capture browser screenshot
     * @param fileName
     */
    public void captureScreenshot(String fileName) {
        log.info("capturing web browser screenshot in file: {}", fileName);
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String testName = fileName.replace(" ", "_").concat(".png");
            FileUtils.copyFile(screenshot, new File(configReader.getProperty("ui.driver.screenshot.path") + testName));
        } catch (WebDriverException e) {
            log.error("error while capturing screenshot: {}", e.getMessage());
        } catch (ClassCastException e) {
            log.error("error while capturing screenshot: {}", e.getMessage());
        } catch (IOException e) {
            log.error("error while capturing screenshot: {}", e.getMessage());
        }
    }
}
