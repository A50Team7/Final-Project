package pages.homepage;

import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnonymousHomePage extends HomePage {
    public AnonymousHomePage(String url) {
        super(url);
    }

    private static By registerBy = By.xpath(Utils.getUIMappingByKey("anon.homepage.registerButton"));
    private static By loginBy = By.xpath(Utils.getUIMappingByKey("anon.homepage.loginButton"));

    public void goToRegisterPage() {
        actions.clickElement(registerBy);
    }

    public void goToLoginPage() {
        actions.clickElement(loginBy);
    }
}
