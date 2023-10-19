package testcases.api;

import com.testframework.api.controllers.RestPostController;
import com.testframework.api.models.PostRequest;
import com.testframework.api.models.EditPostRequest;
import com.testframework.api.models.PostResponse;
import com.testframework.databasehelper.PostHelper;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Visibility;
import org.junit.jupiter.api.*;
import com.testframework.api.controllers.RestUserController;
import com.testframework.databasehelper.UserHelper;
import com.testframework.api.models.UserRequest;
import testcases.ApiHelper;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class RestPostControllerTests extends BaseApiTest {
    private String authCookie;
    private int postId;
    private boolean deleted = false;
    private User user;
    private UserRequest userRequest;
    private Post post;
    private PostRequest postRequest;
    private PostResponse postResponse;

    @BeforeEach
    public void setup() {
        user = UserFactory.createUser();
        post = PostFactory.createPost(user, Visibility.PUBLIC);
        userRequest = new UserRequest("ROLE_USER", user);
        RestUserController.createUser(userRequest);
        authCookie = ApiHelper.getCookieValue(user);

        postRequest = new PostRequest(post);
        postResponse = RestPostController.createPost(postRequest, authCookie);
        postId = postResponse.getPostId();
    }

    // DOESN'T FIND THE CREATED POST - WIP
    // When I do a direct query to the database, the created post is there
    // When I try to view the post from the browser or postman, it doesn't show
    // When debugging, the postId and the content of the created post are intact
    // The other tests are passing with no problem too
    // Only the 'get all' request fails
    // It is verified that the post is public, and it exists in the database.
    @Test
    public void findAllPublicPosts_BodyContainsAtLeastTheCreatedPost() {
        PostResponse[] posts = RestPostController.getAllPosts(authCookie);
        Assertions.assertTrue(Arrays.stream(posts)
                        .anyMatch(x -> x.getPostId() == postId && x.getContent().equals(postResponse.getContent())),
                "Get All Request's body doesn't contain the created post.");
    }

    @Test
    public void createPost() {
        Assertions.assertEquals(post.getContent(), postResponse.getContent(),
                "The post's content doesn't match the intended content.");
        Assertions.assertTrue(PostHelper.entityExists(PostHelper.getPost("post_id", String.format("%s", postId))),
                "The created post is not found in the database.");
    }

    // FIND ALL REQUEST FAILS
    @Test
    public void editPost() {
        EditPostRequest postEditRequest = new EditPostRequest(PostFactory.generateContent());

        RestPostController.editPost(postId, postEditRequest, authCookie);

        PostResponse[] posts = RestPostController.getAllPosts(authCookie);
        Assertions.assertTrue(Arrays.stream(posts)
                        .anyMatch(x -> x.getPostId() == postId && x.getContent().equals(postEditRequest.getContent())),
                "Get All Request's body doesn't contain the new content of the post.");
    }

    @Test
    public void likePost() {
        var likedPost = RestPostController.likePost(postResponse.getPostId(), authCookie);

        Assertions.assertTrue(Arrays.stream(likedPost.getLikes())
                        .anyMatch(x -> x.getUsername().equals(user.getUsername())),
                "The response body doesn't contain the user with whom the post was liked.");
    }

    @Test
    public void deletePost() {
        RestPostController.deletePost(postResponse.getPostId(), authCookie);
        deleted = true;

        PostResponse[] posts = RestPostController.getAllPosts(authCookie);
        Assertions.assertFalse(Arrays.stream(posts)
                        .anyMatch(x -> x.getPostId() == postId && x.getContent().equals(postResponse.getContent())),
                "The deletion was unsuccessful, Get All Request's body still contains the post.");
    }

    @AfterEach
    public void cleanup() {
        if (!deleted) RestPostController.deletePost(postId, authCookie);
        UserHelper.deleteUser("username", String.format("'%s'", userRequest.getUsername()));
    }

}
