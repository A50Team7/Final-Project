package testcases.ui;

import com.testframework.UserActions;
import com.testframework.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    static UserActions actions = new UserActions();

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("weare.baseUrl");
    }

    @BeforeEach
    public void clean() {
        actions.cleanDriver("weare.baseUrl");
    }

    @AfterAll
    public static void tearDown() {
        UserActions.quitDriver();
    }
    
}
