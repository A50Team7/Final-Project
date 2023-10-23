package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class NavigationTests extends BaseTest {


    private final static String ERROR_MESSAGE = "Navigated to a wrong url";
    private static String baseUrl = Utils.getConfigPropertyByKey("weare.baseUrl");
    private User user;
    private String cookieValue;


    @Test
    public void verify_RegisterButton_navigatesToPage() {
        var regButton = By.xpath(Utils.getUIMappingByKey("anon.homepage.registerButton"));
        actions.waitForElementClickable(regButton);
        actions.clickElement(regButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.register.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }


    @Test
    public void verify_HomeButton_navigatesToPage() {
        actions.cleanDriver("weare.aboutus.url");

        var homeButton = By.xpath(Utils.getUIMappingByKey("navigation.homeButton"));
        actions.waitForElementClickable(homeButton);
        actions.clickElement(homeButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        Assertions.assertEquals(baseUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_WEareButton_navigatesToPage() {
        actions.cleanDriver("weare.aboutus.url");

        var weareButton = By.xpath(Utils.getUIMappingByKey("navigation.WEareButton"));
        actions.waitForElementClickable(weareButton);
        actions.clickElement(weareButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        Assertions.assertEquals(baseUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_SignInButton_navigatesToPage() {
        var logButton = By.xpath(Utils.getUIMappingByKey("anon.homepage.loginButton"));
        actions.waitForElementClickable(logButton);
        actions.clickElement(logButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.login.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_LatestPosts_navigatesToPage() {
        var postsButton = By.xpath(Utils.getUIMappingByKey("homepage.latestPosts"));
        actions.waitForElementClickable(postsButton);
        actions.clickElement(postsButton);
        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.latestposts.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_AboutUs_navigatesToPage() {
        var aboutusButton = By.xpath(Utils.getUIMappingByKey("navigation.aboutUsButton"));
        actions.waitForElementClickable(aboutusButton);
        actions.clickElement(aboutusButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.aboutus.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_PersonalProfile_navigatesToPage() {
        user = UserFactory.createUser();
        var createdUser = RestUserController.createUser(new UserRequest("ROLE_USER", user));
        cookieValue = login(user);

        int userId = user.getUserId();

        actions.getDriver().get(baseUrl);

        var profileButton = By.xpath(Utils.getUIMappingByKey("user.homepage.profileButton"));
        actions.waitForElementClickable(profileButton);
        actions.clickElement(profileButton);
        //Assertions
        String profileUrl = actions.getDriver().getCurrentUrl();
        String testUrl = String.format(Utils.getConfigPropertyByKey("weare.profile.url"), userId);
        Assertions.assertEquals(testUrl, profileUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_AddNewPost_navigatesToPage() {

        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));
        cookieValue = login(user);

        actions.getDriver().get(baseUrl);

        var postsButton = By.xpath(Utils.getUIMappingByKey("user.homepage.newPostButton"));
        actions.waitForElementClickable(postsButton);
        actions.clickElement(postsButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.createpost.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_LOGOUT() {
        //Register user -> Login ->
        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));
        cookieValue = login(user);

        actions.getDriver().get(baseUrl);

        var logoutButton = By.xpath(Utils.getUIMappingByKey("user.homepage.logoutButton"));
        actions.waitForElementClickable(logoutButton);
        actions.clickElement(logoutButton);

        var testElement = By.xpath(Utils.getUIMappingByKey("anon.homepage.loginButton"));
        actions.assertElementPresent(testElement);
    }
}
