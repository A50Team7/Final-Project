package com.testframework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.util.Properties;

/**
 * Utility class providing various helper methods for the testing framework.
 */
public class Utils {

    private static final Properties uiMappings = PropertiesManager.PropertiesManagerEnum.INSTANCE.getUiMappings();
    private static final Properties configProperties = PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties();
    public static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Retrieves the current WebDriver instance.
     *
     * @return the current WebDriver instance
     */
    public static WebDriver getWebDriver() {
        return CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
    }

    /**
     * Tears down the current WebDriver instance.
     */
    public static void tearDownWebDriver() {
        LOGGER.info("Quitting WebDriver");
        CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.quitDriver();
    }

    /**
     * Retrieves the locator from the ui_map file by the specified key.
     *
     * @param key the name of the field
     * @return the string value of the field, or the key if not found
     */
    public static String getUIMappingByKey(String key) {
        String value = uiMappings.getProperty(key);
        return value != null ? value : key;
    }

    /**
     * Retrieves the URL or constants from the config file by the specified key.
     *
     * @param key the name of the field
     * @return the string value of the field, or the key if not found
     */
    public static String getConfigPropertyByKey(String key) {
        String value = configProperties.getProperty(key);
        return value != null ? value : key;
    }
}
