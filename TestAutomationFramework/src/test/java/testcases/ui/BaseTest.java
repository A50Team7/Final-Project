package testcases.ui;

import com.testframework.UserActions;
import com.testframework.Utils;
import com.testframework.databasehelper.UserHelper;
import com.testframework.models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import testcases.api.BaseApiTest;

public class BaseTest extends BaseApiTest {
    static UserActions actions = new UserActions();
    protected User user;

    @BeforeAll
    public static void setupDriver() {
        UserActions.loadBrowser("weare.baseUrl");
    }

    @BeforeEach
    public void cleanDriver() {
        actions.cleanDriver("weare.baseUrl");
    }

    @AfterEach
    public void deleteUser() {
        if (user!=null) UserHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }

    @AfterAll
    public static void tearDownDriver() {
        UserActions.quitDriver();
    }

    /**
     * Method used in UI tests; It authorized the given user through the API and adds the generated cookie to the WebDriver.
     *
     * @param user the credentials with which one will log-in
     *
     * @return String value of the created cookie
     */
    public String login(User user) {
        String name = Utils.getConfigPropertyByKey("auth.cookieName");
        String value = getCookieValue(user);

        actions.addCookie(name, value);

        return value;
    }
}
