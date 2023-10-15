package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.RestPostController;
import com.testframework.api.RestUserController;
import com.testframework.databasehelper.UserHelper;
import com.testframework.api.models.RequestUser;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Visibility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CreateNewPostPage;

public class CreatePostTests extends BaseTest {
    private static String createPostUrl = Utils.getConfigPropertyByKey("weare.createpost.url");
    private static CreateNewPostPage createNewPostPage = new CreateNewPostPage(actions.getDriver(), createPostUrl);

    private User user;
    private Post post;
    private String cookieValue;

    @BeforeEach
    public void setup() {
        user = UserFactory.createUser();
        RestUserController.createUser(new RequestUser("ROLE_USER", user));

        cookieValue = login(user);

        post = PostFactory.createPost(user, Visibility.PUBLIC);

        actions.getDriver().get(createPostUrl);
    }

    @Test
    public void createValidPublicPost_Should_Pass() {
        createNewPostPage.createPostAndSubmit(post);

        // assertion
    }

    @Test
    public void createValidPrivatePost_Should_Pass() {
        post.setVisibility(Visibility.PRIVATE);
        createNewPostPage.createPostAndSubmit(post);

        // assertion
    }

    @Test
    public void createEmptyPost_Should_Fail() {
        post.setContent("");
        createNewPostPage.createPostAndSubmit(post);

        // assertion
    }

    @AfterEach
    public void cleanup() {
        RestPostController.deletePost(post.getPostId(), cookieValue);
        UserHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }

}
