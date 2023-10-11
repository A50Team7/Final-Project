package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.UserControllerHelper;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

import java.sql.ResultSet;

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
    public void registerWithValidData_Should_Pass() {
        registerPage.enterAllDataAndRegister(user);

        registerPage.assertWelcomeMessagePresent();

        ResultSet resultSet = UserControllerHelper.getUser("username", String.format("'%s'", user.getUsername()));

        Assertions.assertTrue(UserControllerHelper.userExists(resultSet),
                "The user is not found in the database.");
    }

    @Test
    public void registerWithWhiteSpaceInUsernameField_Should_Fail() {
        user.setUsername(" ");
        registerPage.enterAllDataAndRegister(user);

        registerPage.assertErrorMessagePresent("no whitespaces");

        ResultSet resultSet = UserControllerHelper.getUser("username", String.format("'%s'", user.getUsername()));

        Assertions.assertFalse(UserControllerHelper.userExists(resultSet),
                "The user is created in the database, when it shouldn't.");
    }

    @AfterEach
    public void userCleanup() {
        UserControllerHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }
}
