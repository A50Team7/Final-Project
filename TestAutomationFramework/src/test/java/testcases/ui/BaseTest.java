package testcases.ui;

import com.testframework.UserActions;
import com.testframework.models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import testcases.ApiHelper;
import testcases.api.BaseApiTest;

public class BaseTest extends BaseApiTest {

    static UserActions actions = new UserActions();

    @BeforeAll
    public static void setupDriver() {
        UserActions.loadBrowser("weare.baseUrl");
    }

    @BeforeEach
    public void cleanDriver() {
        actions.cleanDriver("weare.baseUrl");
    }

    @AfterAll
    public static void tearDownDriver() {
        UserActions.quitDriver();
    }

    public String login(User user) {
        String name = "JSESSIONID";
        String value = ApiHelper.getCookieValue(user);

        actions.addCookie(name, value);

        return value;
    }
}
