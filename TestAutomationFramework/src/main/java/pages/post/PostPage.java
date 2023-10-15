package pages.post;

import com.testframework.Utils;
import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class PostPage extends BasePage {
    public PostPage(WebDriver driver, String url) {
        super(driver, url);
        commentSection = new CommentSection(driver);
        personalComment = new PersonalComment(driver);
        createCommentSection = new CreateCommentSection(driver);
    }

    private By titleBy = By.xpath(Utils.getUIMappingByKey("post.title"));
    private By contentBy = By.xpath(Utils.getUIMappingByKey("post.content"));
    private By commentCountBy = By.xpath(Utils.getUIMappingByKey("post.commentCount"));
    private By showCommentsBy = By.xpath(Utils.getUIMappingByKey("post.showComments"));
    private CommentSection commentSection;
    private PersonalComment personalComment;
    private CreateCommentSection createCommentSection;

    public void showComments() {
        actions.clickElement(showCommentsBy);
    }

    public void assertCommentCount(int expected) {
        String text = actions.getText(commentCountBy);
        int actual = Integer.parseInt(text.replaceFirst(" Comments", ""));
        Assertions.assertEquals(expected, actual, "The comment count doesn't match the expected count");
    }

    public void assertContent(String content) {
        Assertions.assertEquals(content, actions.getText(contentBy), "The content doesn't match");
    }

    public void assertTitle(String title) {
        Assertions.assertEquals(title, actions.getText(titleBy), "The title doesn't match");
    }
}
