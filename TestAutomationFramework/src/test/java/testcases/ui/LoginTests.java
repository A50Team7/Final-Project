package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import com.testframework.models.enums.Authority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.homepage.UserHomePage;

public class LoginTests extends BaseTest {
    private static String loginPageUrl = Utils.getConfigPropertyByKey("weare.login.url");
    private static String baseUrl = Utils.getConfigPropertyByKey("weare.baseUrl");
    private static String cookieName = Utils.getConfigPropertyByKey("auth.cookieName");
    private static LoginPage loginPage = new LoginPage(actions.getDriver(), loginPageUrl);
    private static UserHomePage homePage = new UserHomePage(actions.getDriver(), baseUrl);
    private User unregisteredUser;

    @BeforeEach
    public void registerUser() {
        user = UserFactory.createUser();
        unregisteredUser = UserFactory.createUser();
        RestUserController.createUser(new UserRequest(Authority.ROLE_USER.toString(), user));

        loginPage.navigateToPage();
    }

    @Test
    public void FP_63_loginWithValidCredentials_Should_BeSuccessful() {
        loginPage.enterAllCredentialsAndLogin(user.getUsername(), user.getPassword());

        homePage.assertLogoutButtonPresent();
        assertAuthCookiePresent();
    }

    @Test
    public void FP_66_loginWithBothCredentialsInvalid_Should_BeUnsuccessful() {
        loginPage.enterAllCredentialsAndLogin(unregisteredUser.getUsername(), unregisteredUser.getPassword());

        assertUnsuccessfulLogin();
    }

    @Test
    public void FP_65_loginWithDifferentUsername_Should_BeUnsuccessful() {
        loginPage.enterAllCredentialsAndLogin(unregisteredUser.getUsername(), user.getPassword());

        assertUnsuccessfulLogin();
    }

    @Test
    public void FP_64_loginWithDifferentPassword_Should_BeUnsuccessful() {
        loginPage.enterAllCredentialsAndLogin(user.getUsername(), unregisteredUser.getPassword());

        assertUnsuccessfulLogin();
    }

    @Test
    public void loginWithoutEnteringPassword_Should_BeUnsuccessful() {
        loginPage.enterUsername(user.getUsername());
        loginPage.login();

        assertUnsuccessfulLogin();
    }

    @Test
    public void loginWithoutEnteringUsername_Should_BeUnsuccessful() {
        loginPage.enterPassword(user.getUsername());
        loginPage.login();

        assertUnsuccessfulLogin();
    }

    @Test
    public void loginWithoutEnteringBothCredentials_Should_BeUnsuccessful() {
        loginPage.login();

        assertUnsuccessfulLogin();
    }

    private void assertAuthCookiePresent() {
        Assertions.assertTrue(actions.cookieExists(cookieName), "Auth cookie is not present.");
    }

    private void assertNoAuthCookiePresent() {
        Assertions.assertFalse(actions.cookieExists(cookieName), "Auth cookie is present.");
    }

    private void assertUnsuccessfulLogin() {
        loginPage.assertErrorMessagePresent("Wrong username or password");
    }
}
