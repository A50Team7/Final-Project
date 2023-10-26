package com.testframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v113.network.Network;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * CustomWebDriverManager class providing a way to manage the WebDriver for custom web browsing operations.
 * Uses CustomWebDriverManagerEnum internally for managing the WebDriver instance.
 */
public class CustomWebDriverManager {

    /**
     * CustomWebDriverManagerEnum for managing the WebDriver instance.
     */
    public enum CustomWebDriverManagerEnum {
        INSTANCE;
        private WebDriver driver = setupBrowser();

        /**
         * Quits the WebDriver instance if it exists.
         */
        public void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        /**
         * Retrieves the WebDriver instance. If the instance doesn't exist, it initializes the WebDriver.
         *
         * @return the WebDriver instance
         */
        public WebDriver getDriver() {
            if (driver == null) {
                Utils.LOGGER.info("Initializing WebDriver");
                setupBrowser();
            }
            return driver;
        }

        private WebDriver setupBrowser() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-timezone-tracking");
            options.addArguments("--user-timezone=utc");
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            this.driver = driver;
            return driver;
        }
    }
}
