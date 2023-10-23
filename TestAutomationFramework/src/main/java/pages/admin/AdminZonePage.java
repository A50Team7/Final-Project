package pages.admin;

import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

public class AdminZonePage extends BasePage {
    public AdminZonePage(WebDriver driver, String url) {
        super(driver, url);
    }

    private static By usersBy = By.xpath(Utils.getUIMappingByKey("admin.adminZone.usersButton"));
    private static By refreshPostsBy = By.xpath(Utils.getUIMappingByKey("admin.adminZone.refreshPostsButton"));

    public void viewUsers() {
        actions.clickElement(usersBy);
    }

    public void refreshPosts() {
        actions.clickElement(refreshPostsBy);
    }
}
