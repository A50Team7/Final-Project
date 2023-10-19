package pages.common;

import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditPage extends BasePage {
    public EditPage(WebDriver driver, String url) {
        super(driver, url);
    }

    private By editMessageBy = By.xpath(Utils.getUIMappingByKey("edit.message"));
    private By submitBy = By.xpath(Utils.getUIMappingByKey("edit.submit"));

    public void editAndSubmit(String message) {
        editMessage(message);
        submit();
    }

    public void editMessage(String message) {
        actions.clearAndTypeValueInField(editMessageBy, message);
    }

    public void submit() {
        actions.clickElement(submitBy);
    }
}
