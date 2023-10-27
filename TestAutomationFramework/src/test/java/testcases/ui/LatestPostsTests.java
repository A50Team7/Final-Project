package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestPostController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.PostRequest;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Post;
import com.testframework.models.enums.Authority;
import com.testframework.models.enums.Visibility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LatestPostsPage;
import pages.post.PersonalPostPage;

public class LatestPostsTests extends BaseTest {

    private static String latestPostsUrl = Utils.getConfigPropertyByKey("weare.latestposts.url");
    private static String postUrl = Utils.getConfigPropertyByKey("weare.post.url");
    private static LatestPostsPage latestPostsPage = new LatestPostsPage(latestPostsUrl);
    private static PersonalPostPage postPage;
    private Post post;
    private String cookieValue;

    @BeforeEach
    public void setup() {
        user = UserFactory.createUser();
        post = PostFactory.createPost(user, Visibility.PUBLIC);
        RestUserController.createUser(new UserRequest(Authority.ROLE_USER.toString(), user));

        cookieValue = login(user);

        RestPostController.createPost(new PostRequest(post), cookieValue);

        latestPostsPage.navigateToPage();
    }

    @Test
    public void createdPost_Should_BeVisible() {
        latestPostsPage.assertPostMatches(post);
    }

    @Test
    public void FP_70_likingAPost_Should_BeSuccessful() {
        latestPostsPage.like(post);

        latestPostsPage.assertLiked(post);
    }

    @Test
    public void FP_71_dislikingAPost_Should_BeSuccessful() {
        latestPostsPage.like(post);

        latestPostsPage.dislike(post);

        latestPostsPage.assertDisliked(post);
    }

    @Test
    public void FP_72_exploringAPost_Should_BeSuccessful() {
        int postId = post.getPostId();
        postPage = new PersonalPostPage(String.format(postUrl, postId));

        latestPostsPage.explorePost(postId);

        Assertions.assertAll(
                () -> postPage.assertPageNavigated(),
                () -> postPage.assertPost(post)
        );
    }

    @AfterEach
    public void cleanup() {
        RestPostController.deletePost(post.getPostId(), cookieValue);
    }
}
