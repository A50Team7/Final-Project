package pages.admin;

import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PersonalProfilePage;

public class AdminProfilePage extends PersonalProfilePage {
    public AdminProfilePage(WebDriver driver, String url) {
        super(driver, url);
    }

    private static By disableBy = By.xpath(Utils.getUIMappingByKey("admin.disableUserButton"));
    private static By enableBy = By.xpath(Utils.getUIMappingByKey("admin.enableUserButton"));

    public void disable() {
        assertDisableButtonPresent();
        actions.clickElement(disableBy);
    }

    public void enable() {
        assertEnableButtonPresent();
        actions.clickElement(enableBy);
    }

    public void assertEnableButtonPresent() {
        actions.assertElementPresent(enableBy);
    }

    public void assertDisableButtonPresent() {
        actions.assertElementPresent(disableBy);
    }

    public void assertEditProfilePresent() {
        actions.assertElementPresent(editProfileBy);
    }
}
