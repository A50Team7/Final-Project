package pages;

import com.testframework.Utils;
import com.testframework.models.enums.ProfessionalCategory;
import com.testframework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

public class RegisterPage extends BasePage {
    public RegisterPage(String url) {
        super(url);
    }

    public void enterAllDataAndRegister(User user) {
        enterAllData(user);
        submit();
    }

    public void enterAllData(User user) {
        enterUsername(user.getUsername());
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        confirmPassword(user.getPassword());
        selectProfessionalCategory(user.getCategory());
    }

    public void assertWelcomeMessagePresent() {
        actions.assertElementPresent(welcomeMessageBy);
    }

    public void assertErrorMessagePresent(String message) {
        By errorMessageBy = By.xpath(String.format(errorMessage, message));
        actions.assertElementPresent(errorMessageBy);
    }

    public boolean existsInTheDatabase(User user) {
        try {
            int id = user.getUserId();
            return id != -1;
        } catch (Exception e) {
            Utils.LOGGER.info(e);
            return false;
        }
    }


    private static By usernameBy = By.xpath(Utils.getUIMappingByKey("register.username"));
    private static By emailBy = By.xpath(Utils.getUIMappingByKey("register.email"));
    private static By passwordBy = By.xpath(Utils.getUIMappingByKey("register.password"));
    private static By passwordConfirmationBy = By.xpath(Utils.getUIMappingByKey("register.passwordConfirmation"));
    private static By categoryDropdownBy = By.xpath(Utils.getUIMappingByKey("register.categoryDropdown"));
    private static By registerButtonBy = By.xpath(Utils.getUIMappingByKey("register.registerButton"));
    private static By welcomeMessageBy = By.xpath(Utils.getUIMappingByKey("register.welcomeMessage"));
    private static String errorMessage = Utils.getUIMappingByKey("registerAndLogin.errorMessage");

    public void enterUsername(String username) {
        actions.typeValueInField(usernameBy, username);
    }

    public void enterEmail(String email) {
        actions.typeValueInField(emailBy, email);
    }

    public void enterPassword(String password) {
        actions.typeValueInField(passwordBy, password);
    }

    public void confirmPassword(String password) {
        actions.typeValueInField(passwordConfirmationBy, password);
    }

    public void selectProfessionalCategory(ProfessionalCategory category) {
        actions.clickElement(categoryDropdownBy);
        actions.typeValueInField(categoryDropdownBy, category.getStringValue());
    }

    public void submit() {
        actions.clickElement(registerButtonBy);
    }

    public void clearUsername() {
        actions.clearField(usernameBy);
    }

    public void clearEmail() {
        actions.clearField(emailBy);
    }

    public void clearPassword() {
        actions.clearField(passwordBy);
    }

    public void clearConfirmationPassword() {
        actions.clearField(passwordConfirmationBy);
    }

}
