package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestPostController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.PostRequest;
import com.testframework.api.models.UserRequest;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.ConfirmDelete;
import com.testframework.models.enums.Visibility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.common.DeletePage;
import pages.common.EditPostPage;
import pages.post.PersonalPostPage;

public class PostTests extends BaseTest {
    private boolean deleted = false;
    private static String postPageUrl = Utils.getConfigPropertyByKey("weare.post.url");
    private static String editPostPageUrl = Utils.getConfigPropertyByKey("weare.post.edit.url");
    private static String deletePostPageUrl = Utils.getConfigPropertyByKey("weare.post.delete.url");
    private static PersonalPostPage personalPostPage;
    private static EditPostPage editPostPage;
    private static DeletePage deletePage;
    private User user;
    private Post post;
    private String cookieValue;

    @BeforeEach
    public void setup() {
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
    public void editingContentOfCreatedPost_Should_BeSuccessful() {
        personalPostPage.editPost();
        editPostPage = new EditPostPage(actions.getDriver(), String.format(editPostPageUrl, post.getPostId()));
        editPostPage.assertPageNavigated();

        post.setContent(PostFactory.generateContent());
        editPostPage.editPostAndSubmit(post.getContent(), post.getVisibility());

        personalPostPage.assertPost(post);
    }

    @Test
    public void editingVisibilityOfCreatedPostToPrivate_Should_BeSuccessful() {
        personalPostPage.editPost();
        editPostPage = new EditPostPage(actions.getDriver(), String.format(editPostPageUrl, post.getPostId()));
        editPostPage.assertPageNavigated();

        post.setVisibility(Visibility.PRIVATE);
        editPostPage.editVisibility(post.getVisibility());
        editPostPage.submit();

        personalPostPage.assertPost(post);
    }

    @Test
    public void deletingCreatedPostBySelectingDeleteAndSubmitting_Should_BeSuccessful() {
        personalPostPage.deletePost();
        deletePage = new DeletePage(actions.getDriver(), String.format(deletePostPageUrl, post.getPostId()));
        deletePage.assertPageNavigated();

        deletePage.selectAndConfirm(ConfirmDelete.DELETE);

        deletePage.assertDeletedSuccessfullyMessagePresent();
        Assertions.assertFalse(personalPostPage.existsInTheDatabase(post));
        deleted = true;
    }

    @Test
    public void deletingCreatedPostBySelectingCancelAndSubmitting_Should_BeUnsuccessful() {
        personalPostPage.deletePost();
        deletePage = new DeletePage(actions.getDriver(), String.format(deletePostPageUrl, post.getPostId()));
        deletePage.assertPageNavigated();

        deletePage.selectAndConfirm(ConfirmDelete.CANCEL);

        personalPostPage.assertPost(post);
        Assertions.assertTrue(personalPostPage.existsInTheDatabase(post));
    }

    @AfterEach
    public void cleanup() {
        if (!deleted) RestPostController.deletePost(post.getPostId(), cookieValue);
        UserHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }
}
