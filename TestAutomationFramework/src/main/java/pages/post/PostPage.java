package pages.post;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.models.Post;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

@Getter
public class PostPage extends BasePage {
    public PostPage(WebDriver driver, String url) {
        super(driver, url);
        commentSection = new CommentSection(driver);
        personalComment = new PersonalComment(driver);
        createCommentSection = new CreateCommentSection(driver);
    }

    private By authorBy = By.xpath(Utils.getUIMappingByKey("post.author"));
    private By contentBy = By.xpath(Utils.getUIMappingByKey("post.content"));
    private By commentCountBy = By.xpath(Utils.getUIMappingByKey("post.commentCount"));
    private By showCommentsBy = By.xpath(Utils.getUIMappingByKey("post.showComments"));
    private String lengthError = Utils.getUIMappingByKey("post.comment.lengthError");
    private CommentSection commentSection;
    private PersonalComment personalComment;
    private CreateCommentSection createCommentSection;

    public boolean existsInTheDatabase(Post post) {
        try {
            int id = post.getPostId();
            return id != -1;
        } catch(Exception e) {
            Utils.LOGGER.info(e);
            return false;
        }
    }

    public void showComments() {
        actions.clickElement(showCommentsBy);
    }

    public void assertPost(Post post) {
        assertContent(post.getContent());
        assertAuthor(post.getAuthor().getUsername());
        assertCommentCount(post.getComments().size());
    }

    public void assertLengthErrorPresent(int expected) {
        By errorBy = getLengthErrorBy(expected);
        actions.waitForElementPresent(errorBy);
        actions.assertElementPresent(errorBy);
    }

    public void assertCommentCount(int expected) {
        //WaitHelper.waitForUserInteraction();
        int actual = FormatHelper.extractNumber(actions.getText(commentCountBy));
        Assertions.assertEquals(expected, actual, "The comment count doesn't match the expected count");
    }

    public void assertContent(String content) {
        Assertions.assertEquals(content, actions.getText(contentBy), "The content doesn't match");
    }

    public void assertAuthor(String title) {
        Assertions.assertEquals(title, actions.getText(authorBy), "The title doesn't match");
    }

    public By getLengthErrorBy(int length) {
        return By.xpath(String.format(lengthError, length));
    }
}
