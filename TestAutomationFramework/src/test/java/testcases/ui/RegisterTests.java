package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.UserControllerHelper;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

public class RegisterTests extends BaseTest {
    private static final String registerPageUrl = Utils.getConfigPropertyByKey("weare.register.url");
    private static final RegisterPage registerPage = new RegisterPage(actions.getDriver(), registerPageUrl);
    private User user;

    @BeforeEach
    public void userSetup() {
        actions.getDriver().get(registerPageUrl);
        user = UserFactory.createUser();
    }

    @Test
    public void registerWithValidData() {
        registerPage.enterAllDataAndRegister(user);
        registerPage.assertWelcomeMessagePresent();
        // To Do: assertion using REST-Assured request - confirmation that the user is, in fact, in the database
    }

    @AfterEach
    public void userCleanup() {
        UserControllerHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }
}
