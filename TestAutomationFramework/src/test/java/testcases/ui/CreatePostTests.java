package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestPostController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.databasehelper.UserHelper;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Visibility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CreateNewPostPage;
import pages.post.PersonalPostPage;

public class CreatePostTests extends BaseTest {
    private static String createPostUrl = Utils.getConfigPropertyByKey("weare.createpost.url");
    private static String personalPostPageUrl = Utils.getConfigPropertyByKey("weare.post.url");
    private static CreateNewPostPage createNewPostPage = new CreateNewPostPage(actions.getDriver(), createPostUrl);
    private static PersonalPostPage personalPostPage;
    private Post post;
    private String cookieValue;

    @BeforeEach
    public void setup() {
        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));

        cookieValue = login(user);

        post = PostFactory.createPost(user, Visibility.PUBLIC);

        actions.getDriver().get(createPostUrl);
    }

    @Test
    public void createValidPublicPost_Should_BeSuccessful() {
        createNewPostPage.createPostAndSubmit(post);

        assertPostPage();
    }

    @Test
    public void createValidPrivatePost_Should_BeSuccessful() {
        post.setVisibility(Visibility.PRIVATE);
        createNewPostPage.createPostAndSubmit(post);

        assertPostPage();
    }

    // WIP
    //@Test
    public void createEmptyPost_Should_BeUnsuccessful() {
        post.setContent(" ");
        createNewPostPage.createPostAndSubmit(post);

        // assertion
    }

    @AfterEach
    public void cleanup() {
        RestPostController.deletePost(post.getPostId(), cookieValue);
    }

    private void assertPostPage() {
        personalPostPage = new PersonalPostPage(actions.getDriver(), String.format(personalPostPageUrl, post.getPostId()));
        personalPostPage.navigateToPage();
        personalPostPage.assertPost(post);
    }

}
