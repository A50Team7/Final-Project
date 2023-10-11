package testcases.ui;

import com.testframework.UserActions;
import com.testframework.Utils;
import com.testframework.api.RestUserController;
import com.testframework.api.UserControllerHelper;
import com.testframework.api.models.ResponseUsers;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

import java.sql.ResultSet;

public class RegisterTests extends BaseTest {
    private static String registerPageUrl = Utils.getConfigPropertyByKey("weare.register.url");
    private static RegisterPage registerPage = new RegisterPage(actions.getDriver(), registerPageUrl);
    private User user;
    private static final String EXISTS_ERROR = "The user is created in the database, when it shouldn't.";
    private static final String DOESNT_EXIST_ERROR = "The user is not found in the database.";

    @BeforeEach
    public void userSetup() {
        actions.getDriver().get(registerPageUrl);
        user = UserFactory.createUser();
    }

    @Test
    public void registerWithValidData_Should_Pass() {
        registerPage.enterAllDataAndRegister(user);

        registerPage.assertWelcomeMessagePresent();

        Assertions.assertTrue(registerPage.existsInTheDatabase(user), DOESNT_EXIST_ERROR);
    }

    @Test
    public void registerWithWhiteSpaceInUsernameField_Should_Fail() {
        user.setUsername(" ");
        registerPage.enterAllDataAndRegister(user);

        registerPage.assertErrorMessagePresent("no whitespaces");

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithEmptyConfirmPasswordField_Should_Fail() {
        registerPage.enterAllData(user);
        registerPage.clearConfirmationPassword();
        registerPage.submit();

        registerPage.assertErrorMessagePresent("not confirmed");

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithEmptyPasswordField_Should_Fail() {
        registerPage.enterAllData(user);
        registerPage.clearPassword();
        registerPage.submit();

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithEmptyEmailField_Should_Fail() {
        registerPage.enterAllData(user);
        registerPage.clearEmail();
        registerPage.submit();

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithEmptyUsernameField_Should_Fail() {
        registerPage.enterAllData(user);
        user.setUsername(null);
        registerPage.clearUsername();
        registerPage.submit();

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithAlreadyUsedUsername_Should_Fail() {
        registerPage.enterAllDataAndRegister(user);
        actions.getDriver().get(registerPageUrl);

        User newUser = UserFactory.createUser();
        newUser.setUsername(user.getUsername());
        registerPage.enterAllDataAndRegister(newUser);

        registerPage.assertErrorMessagePresent("username already exist");
    }

    @Test
    public void registerWithDifferentConfirmationPassword_Should_Fail() {
        registerPage.enterAllData(user);
        registerPage.confirmPassword(GenerateRandom.generateRandomBoundedAlphanumericString(8));
        registerPage.submit();

        registerPage.assertErrorMessagePresent("not confirmed");

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithPasswordNotContainingUppercaseLetter_Should_Fail() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("password.lowerbound"));
        user.setPassword(GenerateRandom.generateRandomBoundedAlphabeticString(lowerbound)+"!"+"3");
        registerPage.enterAllDataAndRegister(user);

        registerPage.assertErrorMessagePresent("Uppercase letter");

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithPasswordNotContainingDigits_Should_Fail() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("password.lowerbound"));
        user.setPassword(GenerateRandom.generateRandomBoundedAlphabeticString(lowerbound) + "A!");
        registerPage.enterAllDataAndRegister(user);

        registerPage.assertErrorMessagePresent("digit");

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithPasswordNotContainingSpecialSymbol_Should_Fail() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("password.lowerbound"));
        user.setPassword(GenerateRandom.generateRandomBoundedAlphanumericString(lowerbound));
        registerPage.enterAllDataAndRegister(user);

        registerPage.assertErrorMessagePresent("symbol");

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithPasswordShorterThanTheDefinedLength_Should_Fail() {
        int validLength = Integer.parseInt(Utils.getConfigPropertyByKey("password.lowerbound"));
        int invalidLength = validLength - 1;

        user.setPassword(UserFactory.generatePassword(invalidLength));
        registerPage.enterAllDataAndRegister(user);

        registerPage.assertErrorMessagePresent(String.format("minimum %s characters", validLength));

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithInvalidEmail_Should_Fail() {
        user.setEmail(user.getUsername());

        registerPage.enterAllDataAndRegister(user);

        registerPage.assertErrorMessagePresent("email");

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithUsernameLongerThanTheUpperBound_Should_Fail() {
        int upperbound = Integer.parseInt(Utils.getConfigPropertyByKey("username.upperbound"));
        user.setUsername(UserFactory.generateUsername(upperbound + 1));

        registerPage.enterAllDataAndRegister(user);

        registerPage.assertErrorMessagePresent(String.format("%s", upperbound));

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithUsernameShorterThanTheLowerBound_Should_Fail() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("username.lowerbound"));
        user.setUsername(UserFactory.generateUsername(lowerbound - 1));

        registerPage.enterAllDataAndRegister(user);

        registerPage.assertErrorMessagePresent(String.format("%s", lowerbound));

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

    @Test
    public void registerWithEmailAreadyInUse_Should_Fail() {
        registerPage.enterAllDataAndRegister(user);
        actions.getDriver().get(registerPageUrl);

        User newUser = UserFactory.createUser();
        newUser.setEmail(user.getEmail());
        registerPage.enterAllDataAndRegister(newUser);

        registerPage.assertErrorMessagePresent("exist");

        Assertions.assertFalse(registerPage.existsInTheDatabase(newUser), EXISTS_ERROR);
    }

    @AfterEach
    public void userCleanup() {
        UserControllerHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }
}
