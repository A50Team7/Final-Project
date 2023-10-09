package testcases.ui;

import com.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    static UserActions actions = new UserActions();

    @BeforeEach
    public void setUp() {

        UserActions.loadBrowser("weare.baseUrl");
    }

    @AfterEach
    public void tearDown() {

        UserActions.quitDriver();
    }

    public static void login(String page) {
        // page.login(Utils.getConfigPropertyByKey("username"), Utils.getConfigPropertyByKey("password"));
    }
}
