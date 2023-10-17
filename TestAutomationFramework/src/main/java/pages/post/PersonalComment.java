package pages.post;

import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalComment extends CommentSection{

    public PersonalComment(WebDriver driver) {
        super(driver);
    }

    private String editComment = Utils.getUIMappingByKey("post.personalComment.editComment");
    private String deleteComment = Utils.getUIMappingByKey("post.personalComment.deleteComment");

    public void editComment(int id) {
        actions.clickElement(editCommentBy(id));
    }

    public void deleteComment(int id) {
        actions.clickElement(deleteCommentBy(id));
    }

    private By editCommentBy(int id) {
        return By.xpath(String.format(editComment, id));
    }

    private By deleteCommentBy(int id) {
        return By.xpath(String.format(deleteComment, id));
    }
}
