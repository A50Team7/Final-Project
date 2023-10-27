package pages.common;

import com.testframework.Utils;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public abstract class BasePage extends BaseSection{

    protected String url;

    public BasePage(String url) {
        super();
        this.url = url;
    }

    protected static By homeButtonBy = By.xpath(Utils.getUIMappingByKey("navigation.homeButton"));
    protected static By weareButtonBy = By.xpath(Utils.getUIMappingByKey("navigation.WEareButton"));
    protected static By aboutUsButtonBy = By.xpath(Utils.getUIMappingByKey("navigation.aboutUsButton"));

    public void clickHomeButton() {
        actions.clickElement(homeButtonBy);
    }

    public void clickWEareButton() {
        actions.clickElement(weareButtonBy);
    }

    public void clickAboutUsButton() {
        actions.clickElement(aboutUsButtonBy);
    }

    public void navigateToPage() {
        this.driver.get(url);
    }

    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains(url),
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + url);
    }
}
