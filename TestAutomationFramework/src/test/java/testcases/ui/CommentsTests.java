package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestPostController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.PostRequest;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.CommentFactory;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Comment;
import com.testframework.models.Post;
import com.testframework.models.enums.ConfirmDelete;
import com.testframework.models.enums.Visibility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.common.DeletePage;
import pages.common.EditPage;
import pages.post.PersonalPostPage;

public class CommentsTests extends BaseTest {
    private static String postPageUrl = Utils.getConfigPropertyByKey("weare.post.url");
    private static String editCommentPageUrl = Utils.getConfigPropertyByKey("weare.comment.edit.url");
    private static String deleteCommentPageUrl = Utils.getConfigPropertyByKey("weare.comment.delete.url");
    private static PersonalPostPage personalPostPage;
    private static DeletePage deletePage;
    private static EditPage editCommentPage;
    private Post post;
    private Comment comment;
    private String cookieValue;

    @BeforeEach
    public void registerAndLoginAndCreatePost() {
        user = UserFactory.createUser();
        post = PostFactory.createPost(user, Visibility.PUBLIC);
        RestUserController.createUser(new UserRequest("ROLE_USER", user));

        cookieValue = login(user);

        RestPostController.createPost(new PostRequest(post), cookieValue);

        personalPostPage = new PersonalPostPage(actions.getDriver(), String.format(postPageUrl, post.getPostId()));
        personalPostPage.navigateToPage();
        personalPostPage.assertPost(post);
    }

    @Test
    public void FP_49_creatingCommentWithMaxSymbolsAllowed_Should_BeSuccessful() {
        comment = CommentFactory.createComment(post, user);
        comment.setContent(CommentFactory.generateContent(Integer.parseInt(Utils.getConfigPropertyByKey("comment.content.upperbound"))));
        personalPostPage.getCreateCommentSection().createCommentAndPost(comment.getContent());

        personalPostPage.assertCommentCount(post.getComments().size());
        personalPostPage.showComments();
        personalPostPage.getCommentSection().assertComment(comment, comment.getCommentId());
    }

    @Test
    public void FP_50_creatingCommentWithMoreThanTheMaxSymbolsAllowed_Should_BeUnsuccessful() {
        comment = CommentFactory.createComment(post, user);
        int upperbound = Integer.parseInt(Utils.getConfigPropertyByKey("comment.content.upperbound"));
        comment.setContent(CommentFactory.generateContent(upperbound + 1));
        personalPostPage.getCreateCommentSection().createCommentAndPost(comment.getContent());

        personalPostPage.assertCommentCount(0);
        personalPostPage.assertLengthErrorPresent(upperbound);
    }

    @Test
    public void FP_31_creatingCommentWithMinSymbols_Should_BeSuccessful() {
        comment = CommentFactory.createComment(post, user);
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("comment.content.lowerbound"));
        comment.setContent(CommentFactory.generateContent(lowerbound));
        personalPostPage.getCreateCommentSection().createCommentAndPost(comment.getContent());

        personalPostPage.assertCommentCount(post.getComments().size());
        personalPostPage.showComments();
        personalPostPage.getCommentSection().assertComment(comment, comment.getCommentId());
    }

    @Test
    public void FP_32_creatingBlankComment_Should_BeUnsuccessful() {
        comment = CommentFactory.createComment(post, user);
        comment.setContent("");
        personalPostPage.getCreateCommentSection().createCommentAndPost(comment.getContent());

        personalPostPage.assertCommentCount(0);
    }

    @Test
    public void FP_47_likeAnExistingComment_Should_BeSuccessful() {
        comment = CommentFactory.createComment(post, user);
        personalPostPage.getCreateCommentSection().createCommentAndPost(comment.getContent());

        personalPostPage.showComments();
        personalPostPage.getCommentSection().likeComment(comment);
        personalPostPage.getCommentSection().assertLiked(comment);
    }

    @Test
    public void FP_48_dislikeALikedComment_Should_BeSuccessful() {
        comment = CommentFactory.createComment(post, user);
        personalPostPage.getCreateCommentSection().createCommentAndPost(comment.getContent());

        personalPostPage.showComments();
        personalPostPage.getCommentSection().likeComment(comment);
        personalPostPage.getCommentSection().dislikeComment(comment);
        personalPostPage.getCommentSection().assertDisliked(comment);
    }

    @Test
    public void FP_35_editAnExistingComment_Should_BeSuccessful() {
        comment = CommentFactory.createComment(post, user);
        personalPostPage.getCreateCommentSection().createCommentAndPost(comment.getContent());

        personalPostPage.showComments();
        personalPostPage.getPersonalComment().editComment(comment.getCommentId());
        editCommentPage = new EditPage(actions.getDriver(), String.format(editCommentPageUrl, comment.getCommentId()));
        comment.setContent(CommentFactory.generateContent());

        editCommentPage.editAndSubmit(comment.getContent());

        personalPostPage.showComments();
        personalPostPage.getCommentSection().assertComment(comment, comment.getCommentId());
    }

    @Test
    public void FP_34_deleteAnExistingCommentBySelectingDeleteAndSubmitting_Should_BeSuccessful() {
        comment = CommentFactory.createComment(post, user);
        personalPostPage.getCreateCommentSection().createCommentAndPost(comment.getContent());

        personalPostPage.showComments();
        personalPostPage.getPersonalComment().deleteComment(comment.getCommentId());
        deletePage = new DeletePage(actions.getDriver(), String.format(deleteCommentPageUrl, comment.getCommentId()));
        deletePage.selectAndConfirm(ConfirmDelete.DELETE);

        deletePage.assertDeletedSuccessfullyMessagePresent();
        Assertions.assertFalse(personalPostPage.getCommentSection().existsInTheDatabase(comment));
    }

    @Test
    public void deleteAnExistingCommentBySelectingCancelAndSubmitting_Should_BeUnsuccessful() {
        comment = CommentFactory.createComment(post, user);
        personalPostPage.getCreateCommentSection().createCommentAndPost(comment.getContent());

        personalPostPage.showComments();
        personalPostPage.getPersonalComment().deleteComment(comment.getCommentId());
        deletePage = new DeletePage(actions.getDriver(), String.format(deleteCommentPageUrl, comment.getCommentId()));
        deletePage.selectAndConfirm(ConfirmDelete.CANCEL);

        personalPostPage.showComments();
        personalPostPage.getCommentSection().assertComment(comment, comment.getCommentId());
        Assertions.assertTrue(personalPostPage.getCommentSection().existsInTheDatabase(comment));
    }

    @AfterEach
    public void deletePost() {
        RestPostController.deletePost(post.getPostId(), cookieValue);
    }
}
