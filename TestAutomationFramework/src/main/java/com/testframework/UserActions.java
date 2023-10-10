package com.testframework;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static java.lang.String.format;

public class UserActions {

    final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public UserActions() {
        this.driver = Utils.getWebDriver();
    }

    public static void loadBrowser(String baseUrlKey) {
        Utils.getWebDriver().get(Utils.getConfigPropertyByKey(baseUrlKey));
    }

    public static void quitDriver() {
        Utils.tearDownWebDriver();
    }

    public void clickElement(By locator) {
        waitForElementClickable(locator);

        Utils.LOGGER.info("Clicking on element " + locator);
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public void typeValueInField(By locator, String value) {
        waitForElementClickable(locator);

        Utils.LOGGER.info("Typing value: " + value + " In field " + locator);
        WebElement element = driver.findElement(locator);
        element.sendKeys(value);
    }

    public void clearAndTypeValueInField(By locator, String value) {
        waitForElementClickable(locator);

        Utils.LOGGER.info("Clearing text in field" + locator + "And typing value: " + value);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void dragAndDropElement(By fromElementBy, By toElementBy) {
        waitForElementClickable(fromElementBy);
        waitForElementPresent(toElementBy);

        Utils.LOGGER.info("Dragging element " + fromElementBy + " and dropping it on element " + toElementBy);
        WebElement fromElement = driver.findElement(fromElementBy);

        WebElement toElement = driver.findElement(toElementBy);

        Actions actions = new Actions(driver);

        Action dragAndDrop = actions.clickAndHold(fromElement)
                .moveToElement(toElement)
                .release(toElement)
                .build();
        dragAndDrop.perform();
    }

    //############# WAITS #########
    public void waitForElementVisible(By locator) {
        int defaultTimeout = Integer.parseInt(Utils.getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementVisibleUntilTimeout(locator, defaultTimeout);
    }

    public void waitForElementClickable(By locator) {
        int defaultTimeout = Integer.parseInt(Utils.getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementToBeClickableUntilTimeout(locator, defaultTimeout);
    }

    public void waitForElementPresent(By locator) {
        int defaultTimeout = Integer.parseInt(Utils.getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementPresenceUntilTimeout(locator, defaultTimeout);
    }

    //############# WAITS #########

    public void assertElementPresent(By locator) {
        Assertions.assertNotNull(driver.findElement(locator),
                format("Element with %s isn't present.", locator));
    }

    public void assertElementAttribute(By locator, String attributeName, String attributeValue) {
        WebElement element = driver.findElement(locator);

        String value = element.getAttribute(attributeName);
        //WIP:
        Assertions.assertEquals(format("Element with locator %s doesn't match", attributeName), getLocatorValueByKey(attributeValue), value);
    }

    //WIP:
    private String getLocatorValueByKey(String locator) {
        return format(Utils.getUIMappingByKey(locator));
    }

    private String getLocatorValueByKey(String locator, Object[] arguments) {
        //WIP
        return String.format(Utils.getUIMappingByKey(locator), arguments);
    }

    private void waitForElementVisibleUntilTimeout(By locator, int seconds) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + locator + "' was not found.");
        }
    }

    private void waitForElementToBeClickableUntilTimeout(By locator, int seconds) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + locator + "' was not found.");
        }
    }

    private void waitForElementPresenceUntilTimeout(By locator, int seconds) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + locator + "' was not found.");
        }
    }
}
