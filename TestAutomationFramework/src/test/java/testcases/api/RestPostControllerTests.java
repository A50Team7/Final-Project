package testcases.api;

import com.testframework.api.controllers.RestPostController;
import com.testframework.api.models.PostRequest;
import com.testframework.api.models.PostEditor;
import com.testframework.api.models.PostResponse;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import com.testframework.api.controllers.RestUserController;
import com.testframework.databasehelper.UserHelper;
import com.testframework.api.models.UserRequest;

import static io.restassured.RestAssured.given;

public class RestPostControllerTests extends BaseApiTest {
    private UserRequest user;
    private Cookie authCookie;
    private PostRequest post;
    private PostResponse createdPost;

    @BeforeEach
    public void setup() {
        user = new UserRequest("ROLE_USER", UserFactory.createUser());
        Response createdUser = RestUserController.createUser(user);
        String username = user.getUsername();
        String password = user.getPassword();
        Response auth = RestUserController.authUser(username, password);
        authCookie = auth.getDetailedCookie("JSESSIONID");

        post = new PostRequest();
        createdPost = new RestPostController().createPost(post, authCookie.getValue());
        post.setPostId(createdPost.getPostId());


    }

    @AfterEach
    public void cleanup() {
        UserHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }

    @Test
    public void findAllPublicPosts() {
        PostResponse[] posts = RestPostController.getAllPosts();
       // Assertions.assertTrue(posts.length > 0, "There are no posts.");

    }

    @Test
    public void createPost() {

        Assertions.assertEquals(post.getContent(), createdPost.getContent(), "The content doesn't match the created post.");

    }

    @Test
    public void editPost() {
        String test = GenerateRandom.generateRandomBoundedAlphanumericString(30);

        PostEditor editor = new PostEditor(test);
      //  String newContent = editor.getContent();

        Response editPost = RestPostController.editPost(createdPost.getPostId(),editor, authCookie.getValue());

//        String oldContent = createdPost.getContent();
//        Assertions.assertEquals(test, oldContent, "The json body doesn't match the edited post.");

    }

    @Test
    public void likePost() {
        Response like = RestPostController.likePost(createdPost.getPostId(), authCookie.getValue());
        int likeSize = like.jsonPath().getList("likes").size();
        Assertions.assertTrue(likeSize > 0, "There are no likes on this post.");
    }

    @Test
    public void deletePost() {
        Response delete = RestPostController.deletePost(createdPost.getPostId(), authCookie.getValue());

    }

}
