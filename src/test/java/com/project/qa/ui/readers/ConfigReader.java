package com.project.qa.ui.readers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.Data;

/**
 * @author psawale
 * @project UI_Automation_Setup
 * @date 5/11/2024
 */

@Data
public class ConfigReader {
//    private  Properties properties;



    public String getProperty(String key) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/testdata/application.properties")) {
            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
