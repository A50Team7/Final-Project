package pages.common;

import com.testframework.UserActions;
import org.openqa.selenium.WebDriver;

public class BaseSection {

    protected WebDriver driver;
    public UserActions actions;

    public BaseSection() {
        actions = new UserActions();
        this.driver = actions.getDriver();
    }

}
