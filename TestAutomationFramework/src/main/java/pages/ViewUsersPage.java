package pages;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.models.User;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

public class ViewUsersPage extends BasePage {
    public ViewUsersPage(WebDriver driver, String url) {
        super(driver, url);
    }

    private static String userContainer = Utils.getUIMappingByKey("viewUsers.container");
    private static String professionalCategory = Utils.getUIMappingByKey("viewUsers.professionalCategory.ending");
    private static String userSince = Utils.getUIMappingByKey("viewUsers.userSince.ending");
    private static String viewProfile = Utils.getUIMappingByKey("viewUsers.viewProfile");
    private static String usernameOrFullName = Utils.getUIMappingByKey("viewUsers.usernameOrFullName.ending");
    private static final By errorMessageBy = By.xpath(Utils.getUIMappingByKey("search.errorMessage"));

    public void assertErrorMessagePresent() {
        actions.assertElementPresent(errorMessageBy);
    }

    public void assertUserExists(User user) {
        Assertions.assertAll(
                () -> assertUserContainerExists(user.getUserId()),
                () -> Assertions.assertTrue(user.getCategory().getStringValue()
                                .equalsIgnoreCase(getProfessionalCategory(user.getUserId())),
                        "The professional category doesn't match"),
                () -> Assertions.assertEquals(FormatHelper.formatRegistrationDate(user.getRegistrationDate()),
                        getUserSince(user.getUserId()),
                        "The registration date doesn't match"),
                () -> assertViewProfileButtonPresent(user.getUserId())
        );
    }

    public void assertUsername(User user) {
        Assertions.assertEquals(user.getUsername(), getUsernameOrFullName(user.getUserId()),
                "Username doesn't match.");
    }

    public void assertFullName(User user) {
        Assertions.assertEquals(user.getProfile().getFullName(), getUsernameOrFullName(user.getUserId()),
                "Full name doesn't match.");
    }

    public void assertUserContainerExists(int userId) {
        actions.assertElementPresent(userContainerBy(userId));
    }

    public void assertViewProfileButtonPresent(int userId) {
        actions.assertElementPresent(viewProfileBy(userId));
    }

    public String getUsernameOrFullName(int userId) {
        return actions.getText(usernameOrFullNameBy(userId));
    }

    public String getProfessionalCategory(int userId) {
        return actions.getText(professionalCategoryBy(userId));
    }

    public String getUserSince(int userId) {
        return actions.getText(userSinceBy(userId));
    }

    public void goToProfile(int userId) {
        actions.clickElement(viewProfileBy(userId));
    }

    public By userContainerBy(int userId) {
        return By.xpath(String.format(userContainer, userId));
    }

    public By usernameOrFullNameBy(int userId) {
        return By.xpath(String.format(userContainer, userId) + usernameOrFullName);
    }

    public By professionalCategoryBy(int userId) {
        return By.xpath(String.format(userContainer, userId) + professionalCategory);
    }

    public By userSinceBy(int userId) {
        return By.xpath(String.format(userContainer, userId) + userSince);
    }

    public By viewProfileBy(int userId) {
        return By.xpath(String.format(viewProfile, userId));
    }
}
