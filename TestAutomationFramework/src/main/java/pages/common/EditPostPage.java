package pages.common;

import com.testframework.Utils;
import com.testframework.models.enums.Visibility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditPostPage extends EditPage{
    public EditPostPage(WebDriver driver, String url) {
        super(driver, url);
    }

    private By editVisibilityBy = By.xpath(Utils.getUIMappingByKey("edit.visibility"));

    public void editPostAndSubmit(String message, Visibility visibility) {
        editVisibility(visibility);
        editAndSubmit(message);
    }

    public void editVisibility(Visibility visibility) {
        actions.typeValueInField(editVisibilityBy, visibility.getStringValue());
    }
}
