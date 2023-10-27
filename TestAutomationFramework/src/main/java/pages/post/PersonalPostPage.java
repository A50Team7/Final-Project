package pages.post;


import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalPostPage extends PostPage {
    public PersonalPostPage(String url) {
        super(url);
    }

    private By editPostBy = By.xpath(Utils.getUIMappingByKey("post.editPost"));
    private By deletePostBy = By.xpath(Utils.getUIMappingByKey("post.deletePost"));

    public void editPost() {
        actions.clickElement(editPostBy);
    }

    public void deletePost() {
        actions.clickElement(deletePostBy);
    }

    public void assertEditPostButtonPresent() {
        actions.assertElementPresent(editPostBy);
    }

    public void assertDeletePostButtonPresent() {
        actions.assertElementPresent(deletePostBy);
    }
}
