package testcases.ui;

import com.testframework.Utils;
import com.testframework.WaitHelper;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.homepage.AnonymousHomePage;
import pages.homepage.UserHomePage;

public class NavigationTests extends BaseTest {


    private final static String ERROR_MESSAGE = "Navigated to a wrong url";
    private static String baseUrl = Utils.getConfigPropertyByKey("weare.baseUrl");
    private static AnonymousHomePage anonHomePage = new AnonymousHomePage(actions.getDriver(), baseUrl);
    private static UserHomePage userHomePage = new UserHomePage(actions.getDriver(), baseUrl);
    private User user;
    private String cookieValue;

    @Test
    public void verify_RegisterButton_navigatesToPage() {
        anonHomePage.navigateToPage();
        anonHomePage.goToRegisterPage();

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.register.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }


    @Test
    public void verify_HomeButton_navigatesToPage() {
        actions.cleanDriver("weare.aboutus.url");

        var homeButton = By.xpath(Utils.getUIMappingByKey("navigation.homeButton"));
        actions.clickElement(homeButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        Assertions.assertEquals(baseUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_WEareButton_navigatesToPage() {
        actions.cleanDriver("weare.aboutus.url");
        anonHomePage.clickWEareButton();

        String currentUrl = actions.getDriver().getCurrentUrl();
        Assertions.assertEquals(baseUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_SignInButton_navigatesToPage() {
        anonHomePage.goToLoginPage();

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.login.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_LatestPosts_navigatesToPage() {
        anonHomePage.goToLatestPosts();

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.latestposts.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_AboutUs_navigatesToPage() {
        anonHomePage.clickAboutUsButton();

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.aboutus.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_PersonalProfile_navigatesToPage() {
        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));
        cookieValue = login(user);

        int userId = user.getUserId();

        userHomePage.navigateToPage();
        userHomePage.goToPersonalProfile();

        String profileUrl = actions.getDriver().getCurrentUrl();
        String testUrl = String.format(Utils.getConfigPropertyByKey("weare.profile.url"), userId);
        Assertions.assertEquals(testUrl, profileUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_AddNewPost_navigatesToPage() {
        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));
        cookieValue = login(user);

        userHomePage.navigateToPage();
        userHomePage.goToNewPost();

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.createpost.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_LOGOUT() {
        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));
        cookieValue = login(user);

        userHomePage.navigateToPage();
        userHomePage.logout();

        var testElement = By.xpath(Utils.getUIMappingByKey("anon.homepage.loginButton"));
        actions.assertElementPresent(testElement);
    }
}
