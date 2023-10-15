package pages.post;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BaseSection;

public class CommentSection extends BaseSection {
    public CommentSection(WebDriver driver) {
        super(driver);
    }

    private String commentAuthor = Utils.getUIMappingByKey("post.commentSection.commentAuthor");
    private String commentDateTime = Utils.getUIMappingByKey("post.commentSection.commentDateTime");
    private String commentContent = Utils.getUIMappingByKey("post.commentSection.commentContent");
    private String commentLikesCount= Utils.getUIMappingByKey("post.commentSection.commentLikesCount");
    private String likeComment= Utils.getUIMappingByKey("post.commentSection.likeComment");
    private String dislikeComment= Utils.getUIMappingByKey("post.commentSection.dislikeComment");

    public void assertComment(Comment comment, int id) {
        Assertions.assertEquals(comment.getAuthor().getUsername(), getCommentAuthor(id), "Author doesn't match");
        Assertions.assertEquals(FormatHelper.formatDateTime(comment.getCreationDateTime()).substring(0, 17),
                getCommentDateTime(id).substring(0, 17), "DateTime of creation doesn't match");
        Assertions.assertEquals(comment.getContent(), getCommentContent(id), "Content doesn't match");
        Assertions.assertEquals(comment.getLikes(), FormatHelper.extractNumber(getCommentLikesCount(id)), "Comment's like count doesn't match");
    }

    public String getCommentAuthor(int id) {
        return actions.getText(getCommentAuthorBy(id));
    }

    public String getCommentDateTime(int id) {
        return actions.getText(getCommentDateTimeBy(id));
    }

    public String getCommentContent(int id) {
        return actions.getText(getCommentContentBy(id));
    }

    public String getCommentLikesCount(int id) {
        return actions.getText(getCommentLikesCountBy(id));
    }

    public void likeComment(int id) {
        actions.clickElement(getLikeBy(id));
    }

    public void dislikeComment(int id) {
        actions.clickElement(getDislikeBy(id));
    }

    private By getCommentAuthorBy(int id) {
        return By.xpath(String.format(commentAuthor, id));
    }

    private By getCommentDateTimeBy(int id) {
        return By.xpath(String.format(commentDateTime, id));
    }

    private By getCommentContentBy(int id) {
        return By.xpath(String.format(commentContent, id));
    }

    private By getCommentLikesCountBy(int id) {
        return By.xpath(String.format(commentLikesCount, id));
    }

    private By getLikeBy(int id) {
        return By.xpath(String.format(likeComment, id));
    }

    private By getDislikeBy(int id) {
        return By.xpath(String.format(dislikeComment, id));
    }
}
