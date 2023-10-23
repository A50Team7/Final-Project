package pages;

import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver, String url) {
        super(driver, url);
    }

    private static By usernameBy = By.xpath(Utils.getUIMappingByKey("login.username"));
    private static By passwordBy = By.xpath(Utils.getUIMappingByKey("login.password"));
    private static By submitBy = By.xpath(Utils.getUIMappingByKey("login.submit"));
    private static String errorMessage = Utils.getUIMappingByKey("registerAndLogin.errorMessage");

    public void assertErrorMessagePresent(String text) {
        By errorBy = By.xpath(String.format(errorMessage, text));
        actions.assertElementPresent(errorBy);
    }

    public void enterAllCredentialsAndLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        login();
    }

    public void enterUsername(String username) {
        actions.clearAndTypeValueInField(usernameBy, username);
    }

    public void enterPassword(String password) {
        actions.clearAndTypeValueInField(passwordBy, password);
    }

    public void login() {
        actions.clickElement(submitBy);
    }
}
