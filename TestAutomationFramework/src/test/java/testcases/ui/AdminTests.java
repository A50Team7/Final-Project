package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestCommentController;
import com.testframework.api.controllers.RestPostController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.CommentRequest;
import com.testframework.api.models.PersonalProfileRequest;
import com.testframework.api.models.PostRequest;
import com.testframework.api.models.UserRequest;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.CommentFactory;
import com.testframework.factories.PostFactory;
import com.testframework.factories.ProfileFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Comment;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Authority;
import com.testframework.models.enums.ConfirmDelete;
import com.testframework.models.enums.Visibility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PersonalProfileEditorPage;
import pages.admin.AdminProfilePage;
import pages.admin.AdminZonePage;
import pages.common.DeletePage;
import pages.common.EditPage;
import pages.common.EditPostPage;
import pages.post.PersonalPostPage;

public class AdminTests extends BaseTest {

    private boolean forDelete = false;

    private static String adminZoneUrl = Utils.getConfigPropertyByKey("weare.adminZone.url");
    private static String userPersonalPageUrl = Utils.getConfigPropertyByKey("weare.profile.url");
    private static String userEditProfilePageUrl = Utils.getConfigPropertyByKey("weare.profile.editor.url");
    private static String postUrl = Utils.getConfigPropertyByKey("weare.post.url");
    private static String editPostUrl = Utils.getConfigPropertyByKey("weare.post.edit.url");
    private static String deletePostUrl = Utils.getConfigPropertyByKey("weare.post.delete.url");
    private static String editCommentUrl = Utils.getConfigPropertyByKey("weare.comment.edit.url");
    private static String deleteCommentUrl = Utils.getConfigPropertyByKey("weare.comment.delete.url");

    private static AdminZonePage adminZonePage = new AdminZonePage(actions.getDriver(), adminZoneUrl);
    private static AdminProfilePage userProfilePage;
    private static PersonalProfileEditorPage editorPage;
    private static PersonalPostPage postPage;
    private static EditPostPage editPage;
    private static DeletePage deletePage;
    private static EditPage editCommentPage;

    private User admin;
    private Post post;
    private Comment comment;
    private String cookieValue;
    private int userId;

    @BeforeEach
    public void setup() {
        admin = UserFactory.createAdmin();
        RestUserController.createUser(new UserRequest(Authority.ROLE_ADMIN.toString(), admin));

        user = UserFactory.createUserWithProfile();
        RestUserController.createUser(new UserRequest(Authority.ROLE_USER.toString(), user));
        userId = user.getUserId();
        cookieValue = getCookieValue(user);

        login(admin);

        userProfilePage = new AdminProfilePage(actions.getDriver(), String.format(userPersonalPageUrl, userId));
        editorPage = new PersonalProfileEditorPage(actions.getDriver(), String.format(userEditProfilePageUrl, userId));
    }

    @Test
    public void navigatingToAdminZonePageAsAdmin_Should_BeSuccessful() {
        adminZonePage.navigateToPage();

        adminZonePage.assertPageNavigated();
    }

    @Test
    public void FP_86_disablingUser_Should_BeSuccessful() {
        userProfilePage.navigateToPage();

        userProfilePage.disable();

        userProfilePage.assertEnableButtonPresent();
        // api check
    }

    @Test
    public void FP_87_enablingUser_Should_BeSuccessful() {
        userProfilePage.navigateToPage();

        userProfilePage.disable();
        userProfilePage.enable();

        userProfilePage.assertDisableButtonPresent();
        // api check
    }

    // WIP
    //@Test
    public void viewingAllUsersInAdminZone_Should_BeSuccessful() {
        adminZonePage.navigateToPage();
        adminZonePage.viewUsers();

        // assert
    }

    // WIP
    //@Test
    public void editingUserProfile_Should_BeSuccessful() {
        RestUserController.upgradeUserPersonalProfile(user.getUserId(), new PersonalProfileRequest(user), cookieValue);

        user.getProfile().setFirstName(ProfileFactory.generateFirstName());

        userProfilePage.navigateToPage();

        userProfilePage.assertEditProfilePresent();

        userProfilePage.editProfile();
        editorPage.enterFirstName(user.getProfile().getFirstName());
        editorPage.updateProfile();
        userProfilePage.navigateToPage();

        // WIP
        userProfilePage.assertEqualData(user);
    }

    @Test
    public void FP_81_editingPost_Should_BeSuccessful() {
        int postId = createPostAndReturnId();
        forDelete = true;
        postPage = new PersonalPostPage(actions.getDriver(), String.format(postUrl, postId));
        postPage.navigateToPage();

        Assertions.assertAll(
                () -> postPage.assertEditPostButtonPresent(),
                () -> {
                    postPage.editPost();
                    editPage = new EditPostPage(actions.getDriver(), String.format(editPostUrl, postId));
                    editPage.assertPageNavigated();
                },
                () -> {
                    post.setContent(PostFactory.generateContent());
                    editPage.editPostAndSubmit(post.getContent(), post.getVisibility());
                    postPage.assertPost(post);
                }
        );
    }

    @Test
    public void FP_82_deletingPost_Should_BeSuccessful() {
        int postId = createPostAndReturnId();
        postPage = new PersonalPostPage(actions.getDriver(), String.format(postUrl, postId));
        postPage.navigateToPage();

        Assertions.assertAll(
                () -> postPage.assertDeletePostButtonPresent(),
                () -> {
                    postPage.deletePost();
                    deletePage = new DeletePage(actions.getDriver(), String.format(deletePostUrl, postId));
                    deletePage.assertPageNavigated();
                },
                () -> {
                    deletePage.selectAndConfirm(ConfirmDelete.DELETE);
                    deletePage.assertDeletedSuccessfullyMessagePresent();
                },
                () -> Assertions.assertFalse(postPage.existsInTheDatabase(post))
        );
    }

    @Test
    public void FP_83_editingComment_Should_BeSuccessful() {
        int postId = createPostAndReturnId();
        forDelete = true;
        int commentId = createCommentAndReturnId();

        postPage = new PersonalPostPage(actions.getDriver(), String.format(postUrl, postId));
        postPage.navigateToPage();
        postPage.showComments();

        comment.setContent(CommentFactory.generateContent());

        Assertions.assertAll(
                () -> postPage.getPersonalComment().assertEditCommentButtonPresent(commentId),
                () -> {
                    postPage.getPersonalComment().editComment(commentId);
                    editCommentPage = new EditPage(actions.getDriver(), String.format(editCommentUrl, commentId));
                    editCommentPage.assertPageNavigated();
                },
                () -> {
                    editCommentPage.editAndSubmit(comment.getContent());

                    postPage.showComments();
                    postPage.getCommentSection().assertComment(comment, commentId);
                }
        );
    }

    @Test
    public void FP_84_deletingComment_Should_BeSuccessful() {
        int postId = createPostAndReturnId();
        forDelete = true;
        int commentId = createCommentAndReturnId();

        postPage = new PersonalPostPage(actions.getDriver(), String.format(postUrl, postId));
        postPage.navigateToPage();
        postPage.showComments();

        Assertions.assertAll(
                () -> postPage.getPersonalComment().assertDeleteCommentButtonPresent(commentId),
                () -> {
                    postPage.getPersonalComment().deleteComment(commentId);
                    deletePage = new DeletePage(actions.getDriver(), String.format(deleteCommentUrl, commentId));
                    deletePage.assertPageNavigated();
                },
                () -> {
                    deletePage.selectAndConfirm(ConfirmDelete.DELETE);
                    deletePage.assertDeletedSuccessfullyMessagePresent();
                },
                () -> {
                    postPage.navigateToPage();
                    postPage.showComments();
                    Assertions.assertFalse(postPage.getCommentSection().existsInTheDatabase(comment));
                }
        );
    }

    @AfterEach
    public void cleanup() {
        if (forDelete) RestPostController.deletePost(post.getPostId(), cookieValue);
        UserHelper.deleteUser("username", String.format("'%s'", admin.getUsername()));
    }

    private int createPostAndReturnId() {
        post = PostFactory.createPost(user, Visibility.PUBLIC);
        var response = RestPostController.createPost(new PostRequest(post), cookieValue);
        return response.getPostId();
    }

    private int createCommentAndReturnId() {
        comment = CommentFactory.createComment(post, user);
        CommentRequest commentRequest = new CommentRequest(comment);
        var commentResponse = RestCommentController.createComment(commentRequest, cookieValue);
        return commentResponse.getCommentId();
    }
}
