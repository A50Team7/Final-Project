package com.testframework;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static java.lang.String.format;

/**
 * UserActions class providing a set of useful actions that can be performed on the web elements using Selenium WebDriver.
 */
public class UserActions {

    /**
     * Retrieves the WebDriver instance.
     *
     * @return the WebDriver instance
     */
    public WebDriver getDriver() {
        return Utils.getWebDriver();
    }

    /**
     * Loads the browser with the specified base URL.
     *
     * @param baseUrlKey the key representing the base URL to load
     */
    public static void loadBrowser(String baseUrlKey) {
        Utils.getWebDriver().get(Utils.getConfigPropertyByKey(baseUrlKey));
    }

    public static void quitDriver() {
        Utils.tearDownWebDriver();
    }

    /**
     * Deletes all cookies to ensure a partially cleaned session.
     *
     * @param baseUrlKey the key representing the URL to which the driver should navigate
     */
    public void cleanDriver(String baseUrlKey) {
        Utils.getWebDriver().manage().deleteAllCookies();
        Utils.getWebDriver().get(Utils.getConfigPropertyByKey(baseUrlKey));
        Utils.LOGGER.info("cleaned driver and navigated to: " + baseUrlKey);
    }

    public void addCookie(String name, String value) {
        Utils.getWebDriver().manage().addCookie(new Cookie.Builder(name, value).build());
    }

    /**
     * Checks whether a cookie with the specified name exists.
     *
     * @param name the name of the cookie to check
     * @return true if a cookie with the given name exists, false otherwise
     */
    public boolean cookieExists(String name) {
        return getDriver().manage().getCookies().stream()
                .anyMatch(cookie -> cookie.getName().equals(name));
    }

    public String getCookieValue(String name) {
        return getDriver().manage().getCookieNamed(name).getValue();
    }

    /**
     * Scrolls the web page until the specified element is visible.
     *
     * @param element the WebElement to scroll to
     */
    public void scrollUntilVisible(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Utils.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", element);
    }

    /**
     * Scrolls the web page until the element located by the specified locator is visible.
     *
     * @param locator the locator used to find the element
     */
    public void scrollUntilVisible(By locator) {
        WebElement element = Utils.getWebDriver().findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) Utils.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", element);
    }

    /**
     * Clicks the element located by the specified locator after implementing necessary wait logics.
     *
     * @param locator the locator used to find the element
     */
    public void clickElement(By locator) {
        Utils.LOGGER.info("Clicking on element " + locator);
        WebElement element = Utils.getWebDriver().findElement(locator);
        scrollUntilVisible(element);
        waitForElementPresent(locator);
        waitForElementClickable(locator);
        tryClick(element);
    }

    /**
     * Attempts to click the provided WebElement with a specified timeout.
     *
     * @param element the WebElement to click
     */
    public void tryClick(WebElement element) {
        var wait = new FluentWait<>(Utils.getWebDriver())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementClickInterceptedException.class);

        wait.until(x -> {
            element.click();
            return true;
        });
    }

    public String getText(By locator) {
        waitForElementClickable(locator);
        WebElement element = Utils.getWebDriver().findElement(locator);
        return element.getText();
    }

    public void typeValueInField(By locator, String value) {
        waitForElementClickable(locator);

        Utils.LOGGER.info("Typing value: " + value + " In field " + locator);
        WebElement element = Utils.getWebDriver().findElement(locator);
        scrollUntilVisible(element);
        element.sendKeys(value);
    }

    public void clearField(By locator) {
        waitForElementClickable(locator);

        Utils.LOGGER.info("Clearing text in field" + locator);
        WebElement element = Utils.getWebDriver().findElement(locator);
        scrollUntilVisible(element);
        element.clear();
    }

    public void clearAndTypeValueInField(By locator, String value) {
        waitForElementClickable(locator);

        Utils.LOGGER.info("Clearing text in field" + locator + "And typing value: " + value);
        WebElement element = Utils.getWebDriver().findElement(locator);
        scrollUntilVisible(element);
        element.clear();
        element.sendKeys(value);
    }

    public void dragAndDropElement(By fromElementBy, By toElementBy) {
        waitForElementClickable(fromElementBy);
        waitForElementPresent(toElementBy);

        Utils.LOGGER.info("Dragging element " + fromElementBy + " and dropping it on element " + toElementBy);
        WebElement fromElement = Utils.getWebDriver().findElement(fromElementBy);

        WebElement toElement = Utils.getWebDriver().findElement(toElementBy);

        Actions actions = new Actions(Utils.getWebDriver());

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
        waitForElementPresent(locator);
        Assertions.assertNotNull(Utils.getWebDriver().findElement(locator),
                format("Element with %s isn't present.", locator));
    }

    public void assertElementAttribute(By locator, String attributeName, String attributeValue) {
        WebElement element = Utils.getWebDriver().findElement(locator);

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
        WebDriverWait wait = new WebDriverWait(Utils.getWebDriver(), timeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + locator + "' was not found.");
        }
    }

    private void waitForElementToBeClickableUntilTimeout(By locator, int seconds) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(Utils.getWebDriver(), timeout);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + locator + "' was not found.");
        }
    }

    private void waitForElementPresenceUntilTimeout(By locator, int seconds) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(Utils.getWebDriver(), timeout);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + locator + "' was not found.");
        }
    }

}
