package pages.common;

import com.testframework.Utils;
import com.testframework.models.enums.ConfirmDelete;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeletePage extends BasePage {
    public DeletePage(WebDriver driver, String url) {
        super(driver, url);
    }

    private By dropdownBy = By.xpath(Utils.getUIMappingByKey("delete.dropdown"));
    private By submitBy = By.xpath(Utils.getUIMappingByKey("delete.confirm"));
    private By deletedMessageBy = By.xpath(Utils.getUIMappingByKey("delete.deletedMessage"));

    public void assertDeletedSuccessfullyMessagePresent() {
        actions.assertElementPresent(deletedMessageBy);
    }

    public void selectAndConfirm(ConfirmDelete confirmDelete) {
        selectFromDropdown(confirmDelete);
        submit();
    }

    public void selectFromDropdown(ConfirmDelete confirmDelete) {
        actions.typeValueInField(dropdownBy, confirmDelete.getStringValue());
    }

    public void submit() {
        actions.clickElement(submitBy);
    }
}
