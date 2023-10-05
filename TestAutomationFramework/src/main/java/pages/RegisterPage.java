package pages;

import com.testframework.Utils;
import com.testframework.models.enums.ProfessionalCategory;
import com.testframework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public void enterAllDataAndRegister(User user) {
        enterUsername(user.getUsername());
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        confirmPassword(user.getPassword());
        selectProfessionalCategory(user.getCategory());
        submit();
    }

    private static final By usernameBy = By.xpath(Utils.getUIMappingByKey("register.username"));
    private static final By emailBy = By.xpath(Utils.getUIMappingByKey("register.email"));
    private static final By passwordBy = By.xpath(Utils.getUIMappingByKey("register.password"));
    private static final By passwordConfirmationBy = By.xpath(Utils.getUIMappingByKey("register.passwordConfirmation"));
    private static final By categoryDropdownBy = By.xpath(Utils.getUIMappingByKey("register.categoryDropdown"));
    private static final By registerButtonBy = By.xpath(Utils.getUIMappingByKey("register.registerButton"));
    private static final By welcomeMessageBy = By.xpath(Utils.getUIMappingByKey("register.welcomeMessage"));

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

    public void assertWelcomeMessagePresent() {
        actions.waitForElementPresent(welcomeMessageBy);
        actions.assertElementPresent(welcomeMessageBy);
    }
}
