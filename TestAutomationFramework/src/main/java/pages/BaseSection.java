package pages;

import com.testframework.UserActions;
import org.openqa.selenium.WebDriver;

public class BaseSection {

    protected WebDriver driver;
    public UserActions actions;

    public BaseSection(WebDriver driver) {
        this.driver = driver;
        actions = new UserActions();
    }

}
