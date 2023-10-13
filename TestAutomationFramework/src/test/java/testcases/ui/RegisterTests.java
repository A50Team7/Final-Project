package testcases.ui;

import com.testframework.Utils;
import com.testframework.DatabaseHelper;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

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

        assertFails("no whitespaces", user);
    }

    @Test
    public void registerWithEmptyConfirmPasswordField_Should_Fail() {
        registerPage.enterAllData(user);
        registerPage.clearConfirmationPassword();
        registerPage.submit();

        assertFails("not confirmed", user);
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

        assertFails("not confirmed", user);
    }

    @Test
    public void registerWithPasswordNotContainingUppercaseLetter_Should_Fail() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("password.lowerbound"));
        user.setPassword(GenerateRandom.generateRandomBoundedAlphabeticString(lowerbound)+"!"+"3");
        registerPage.enterAllDataAndRegister(user);

        assertFails("uppercase letter", user);
    }

    @Test
    public void registerWithPasswordNotContainingDigits_Should_Fail() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("password.lowerbound"));
        user.setPassword(GenerateRandom.generateRandomBoundedAlphabeticString(lowerbound) + "A!");
        registerPage.enterAllDataAndRegister(user);

        assertFails("digit", user);
    }

    @Test
    public void registerWithPasswordNotContainingSpecialSymbol_Should_Fail() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("password.lowerbound"));
        user.setPassword(GenerateRandom.generateRandomBoundedAlphanumericString(lowerbound));
        registerPage.enterAllDataAndRegister(user);

        assertFails("special symbol", user);
    }

    @Test
    public void registerWithPasswordShorterThanTheDefinedLength_Should_Fail() {
        int validLength = Integer.parseInt(Utils.getConfigPropertyByKey("password.lowerbound"));
        int invalidLength = validLength - 1;

        user.setPassword(UserFactory.generatePassword(invalidLength));
        registerPage.enterAllDataAndRegister(user);

        assertFails(String.format("minimum %s characters", validLength), user);
    }

    @Test
    public void registerWithInvalidEmail_Should_Fail() {
        user.setEmail(user.getUsername());

        registerPage.enterAllDataAndRegister(user);

        assertFails("email", user);
    }

    @Test
    public void registerWithUsernameLongerThanTheUpperBound_Should_Fail() {
        int upperbound = Integer.parseInt(Utils.getConfigPropertyByKey("username.upperbound"));
        user.setUsername(UserFactory.generateUsername(upperbound + 1));

        registerPage.enterAllDataAndRegister(user);

        assertFails(String.format("%s", upperbound), user);
    }

    @Test
    public void registerWithUsernameShorterThanTheLowerBound_Should_Fail() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("username.lowerbound"));
        user.setUsername(UserFactory.generateUsername(lowerbound - 1));

        registerPage.enterAllDataAndRegister(user);

        assertFails(String.format("%s", lowerbound), user);
    }

    @Test
    public void registerWithEmailAlreadyInUse_Should_Fail() {
        registerPage.enterAllDataAndRegister(user);
        actions.getDriver().get(registerPageUrl);

        User newUser = UserFactory.createUser();
        newUser.setEmail(user.getEmail());
        registerPage.enterAllDataAndRegister(newUser);

        assertFails("exist", newUser);
    }

    @AfterEach
    public void userCleanup() {
        DatabaseHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }

    private void assertFails(String expectedErrorMessage, User user) {
        registerPage.assertErrorMessagePresent(expectedErrorMessage);

        Assertions.assertFalse(registerPage.existsInTheDatabase(user), EXISTS_ERROR);
    }

}
