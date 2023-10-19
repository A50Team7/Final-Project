package com.testframework;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Driver implements WebDriver {

    @Getter
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public Driver(WebDriver driver) {
        webDriver = driver;
        Duration timeout = Duration.ofSeconds(Integer.parseInt(Utils.getConfigPropertyByKey("config.defaultTimeoutSeconds")));
        webDriverWait = new WebDriverWait(driver, timeout);
    }

    @Override
    public void get(String s) {
        Utils.LOGGER.info("Navigating to page: " + s);
        webDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        String currentUrl = webDriver.getCurrentUrl();
        Utils.LOGGER.info("Getting current URL: " + currentUrl);
        return currentUrl;
    }

    @Override
    public String getTitle() {
        String currentTitle = webDriver.getTitle();
        Utils.LOGGER.info("Getting Title: " + currentTitle);
        return currentTitle;
    }

    @Override
    public List<WebElement> findElements(By by) {
        Utils.LOGGER.info("Finding element with locator: " + by.toString());
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        Utils.LOGGER.info("Finding all elements with locator: " + by.toString());
        WebElement elementFound = webDriver.findElement(by);
        return webDriverWait.until(ExpectedConditions.visibilityOf(elementFound));
    }

    @Override
    public String getPageSource() {
        Utils.LOGGER.info("Getting page source...");
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        Utils.LOGGER.info("Closing Browser...");
        webDriver.close();
    }

    @Override
    public void quit() {
        Utils.LOGGER.info("Quiting driver...");
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        Utils.LOGGER.info("Getting Window handles...");
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }
}
