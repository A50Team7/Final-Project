package pages;

import com.testframework.UserActions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public abstract class BasePage extends BaseSection{

    protected String url;

    public BasePage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    public String getUrl() {
        return url;
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
