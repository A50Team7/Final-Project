package pages.homepage;

import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminHomePage extends UserHomePage {
    public AdminHomePage(String url) {
        super(url);
    }

    private static By adminZoneBy = By.xpath(Utils.getUIMappingByKey("admin.homepage.adminZoneButton"));

    public void goToAdminZone() {
        actions.clickElement(adminZoneBy);
    }

    public void assertAdminZoneButtonPresent() {
        actions.assertElementPresent(adminZoneBy);
    }
}
