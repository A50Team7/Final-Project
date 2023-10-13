package pages;

import com.testframework.Utils;
import com.testframework.models.Post;
import com.testframework.models.enums.Visibility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateNewPostPage extends BasePage {
    public CreateNewPostPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public void createPostAndSubmit(Post post) {
        selectVisibility(post.getVisibility());
        enterMessage(post.getContent());
        submit();
    }

    private static By visibilityBy = By.xpath(Utils.getUIMappingByKey("createNewPost.visibility"));
    private static By messageBy = By.xpath(Utils.getUIMappingByKey("createNewPost.message"));
    private static By imageInputBy = By.xpath(Utils.getUIMappingByKey("createNewPost.imageInput"));
    private static By submitBy = By.xpath(Utils.getUIMappingByKey("createNewPost.submit"));


    public void selectVisibility(Visibility visibility) {
        actions.clickElement(visibilityBy);
        actions.typeValueInField(visibilityBy, visibility.getStringValue());
    }

    public void enterMessage(String message) {
        actions.typeValueInField(messageBy, message);
    }

    //WIP
    public void enterImagePath(String path) {
        actions.typeValueInField(imageInputBy, path);
    }

    public void submit() {
        actions.clickElement(submitBy);
    }
}
