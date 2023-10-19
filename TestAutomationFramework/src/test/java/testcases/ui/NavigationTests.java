package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class NavigationTests extends BaseTest {


    private final static String ERROR_MESSAGE = "Navigated to a wrong url";
    private final static String BASE_URL = Utils.getConfigPropertyByKey("weare.baseUrl");
    private User user;
    private String cookieValue;


    @Test
    public void verify_RegisterButton_navigatesToPage() {
        var regButton = By.xpath(Utils.getUIMappingByKey("navigation.registerButton"));
        actions.waitForElementClickable(regButton);
        actions.clickElement(regButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.register.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }


    @Test
    public void verify_HomeButton_navigatesToPage(){
        actions.cleanDriver("weare.aboutus.url");

        var homeButton = By.xpath(Utils.getUIMappingByKey("navigation.homeButton"));
        actions.waitForElementClickable(homeButton);
        actions.clickElement(homeButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        Assertions.assertEquals(BASE_URL, currentUrl, ERROR_MESSAGE);
    }
    @Test
    public void verify_WEareButton_navigatesToPage(){
        actions.cleanDriver("weare.aboutus.url");

        var weareButton = By.xpath(Utils.getUIMappingByKey("navigation.WEareButton"));
        actions.waitForElementClickable(weareButton);
        actions.clickElement(weareButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        Assertions.assertEquals(BASE_URL, currentUrl, ERROR_MESSAGE);
    }
    @Test
    public void verify_SignInButton_navigatesToPage(){
        var logButton = By.xpath(Utils.getUIMappingByKey("navigation.signInButton"));
        actions.waitForElementClickable(logButton);
        actions.clickElement(logButton);

        String currentUrl = actions.getDriver().getCurrentUrl();
        String testUrl = Utils.getConfigPropertyByKey("weare.login.url");
        Assertions.assertEquals(testUrl, currentUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_LatestPosts_navigatesToPage() {
        var postsButton = By.xpath(Utils.getUIMappingByKey("navigation.latestPostsButton"));
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

        ResponseBody userBody = createdUser.getBody();
        var myArray = userBody.asString().split(" ");
        var responseId = myArray[6];
        int userId = Integer.valueOf(responseId);

        actions.getDriver().get(BASE_URL);

        var profileButton = By.xpath(Utils.getUIMappingByKey("navigation.profileButton"));
        actions.waitForElementClickable(profileButton);
        actions.clickElement(profileButton);
        //Assertions
        String profileUrl = actions.getDriver().getCurrentUrl();
        String testUrl = String.format("http://localhost:8081/auth/users/%s/profile", userId);
        Assertions.assertEquals(testUrl, profileUrl, ERROR_MESSAGE);
    }

    @Test
    public void verify_AddNewPost_navigatesToPage() {

        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));
        cookieValue = login(user);

        actions.getDriver().get(BASE_URL);

        var postsButton = By.xpath(Utils.getUIMappingByKey("navigation.newPostButton"));
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

        actions.getDriver().get(BASE_URL);

        var logoutButton = By.xpath(Utils.getUIMappingByKey("navigation.logoutButton"));
        actions.waitForElementClickable(logoutButton);
        actions.clickElement(logoutButton);

        var testElement = By.xpath(Utils.getUIMappingByKey("navigation.signInButton"));
        actions.assertElementPresent(testElement);
    }
}
