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

public class CustomWebDriverManager {

    public enum CustomWebDriverManagerEnum {
        INSTANCE;
        private WebDriver driver = setupBrowser();

        public void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        public WebDriver getDriver() {
            if (driver == null) {
                Utils.LOGGER.info("Initializing WebDriver");
                setupBrowser();
            }
            return driver;
        }

        private WebDriver setupBrowser() {
            var options = new ChromeOptions();
            options.addArguments("--headless");
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            this.driver = driver;
            return driver;
        }
    }
}
