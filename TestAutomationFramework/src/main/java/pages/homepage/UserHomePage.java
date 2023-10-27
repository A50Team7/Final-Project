package pages.homepage;

import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHomePage extends HomePage{

    public UserHomePage(String url) {
        super(url);
    }

    private static By logoutBy = By.xpath(Utils.getUIMappingByKey("user.homepage.logoutButton"));
    private static By newPostBy = By.xpath(Utils.getUIMappingByKey("user.homepage.newPostButton"));
    private static By profileBy = By.xpath(Utils.getUIMappingByKey("user.homepage.profileButton"));

    public void logout() {
        actions.clickElement(logoutBy);
    }

    public void goToNewPost() {
        actions.clickElement(newPostBy);
    }

    public void goToPersonalProfile() {
        actions.clickElement(profileBy);
    }

    public void assertLogoutButtonPresent() {
        actions.assertElementPresent(logoutBy);
    }
}
