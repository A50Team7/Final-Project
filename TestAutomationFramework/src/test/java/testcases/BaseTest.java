package testcases;

import com.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
public class BaseTest {

    static UserActions actions = new UserActions();

    @BeforeAll
    public static void setUp() {

        UserActions.loadBrowser("weare.baseUrl");
    }

    @AfterAll
    public static void tearDown() {

        UserActions.quitDriver();
    }

    public static void login(String page) {

        // page.login(Utils.getConfigPropertyByKey("username"), Utils.getConfigPropertyByKey("password"));
    }
}
