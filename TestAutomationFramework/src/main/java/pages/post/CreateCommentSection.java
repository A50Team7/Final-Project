package pages.post;

import com.testframework.Utils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BaseSection;

@Getter
public class CreateCommentSection extends BaseSection {

    public CreateCommentSection() {
        super();
    }

    private By commentMessageBy = By.xpath(Utils.getUIMappingByKey("post.commentMessage"));
    private By postCommentBy = By.xpath(Utils.getUIMappingByKey("post.postComment"));

    public void createCommentAndPost(String content) {
        enterContent(content);
        postComment();
    }

    public void enterContent(String content) {
        actions.typeValueInField(commentMessageBy, content);
    }

    public void postComment() {
        actions.clickElement(postCommentBy);
    }
}
