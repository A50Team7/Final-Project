package testcases.api;

import com.testframework.api.controllers.RestCommentController;
import com.testframework.api.controllers.RestPostController;
import com.testframework.api.models.*;
import com.testframework.databasehelper.PostHelper;
import com.testframework.factories.CommentFactory;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Comment;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Authority;
import com.testframework.models.enums.Visibility;
import org.junit.jupiter.api.*;
import com.testframework.api.controllers.RestUserController;
import com.testframework.databasehelper.UserHelper;

import java.util.Arrays;

public class RestPostControllerTests extends BaseApiTest {
    private String authCookie;
    private int postId;
    private boolean deleted = false;
    private User user;
    private Post post;
    private PostRequest postRequest;
    private PostResponse postResponse;

    @BeforeEach
    public void setup() {
        user = UserFactory.createUser();
        post = PostFactory.createPost(user, Visibility.PUBLIC);
        UserRequest userRequest = new UserRequest(Authority.ROLE_USER.toString(), user);
        RestUserController.createUser(userRequest);
        authCookie = getCookieValue(user);

        postRequest = new PostRequest(post);
        postResponse = RestPostController.createPost(postRequest, authCookie);
        postId = postResponse.getPostId();
    }

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
        assertVisibility();
    }

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

    @Test
    public void getCommentsUnderPost() {
        Comment comment = CommentFactory.createComment(post, user);
        CommentRequest commentRequest = new CommentRequest(comment);
        CommentResponse commentResponse = RestCommentController.createComment(commentRequest, authCookie);

        CommentResponse[] comments = RestPostController.getAllCommentsUnderPost(postId, authCookie);
        Assertions.assertTrue(Arrays.stream(comments)
                        .anyMatch(x -> x.getCommentId() == commentResponse.getCommentId() && x.getContent().equals(commentResponse.getContent())),
                "Get All Comments Under Post doesn't contain the created comment.");
    }

    @AfterEach
    public void cleanup() {
        if (!deleted) RestPostController.deletePost(postId, authCookie);
        UserHelper.deleteUserByUsername(user.getUsername());
    }

    private void assertVisibility() {
        Assertions.assertEquals(postRequest.isPublic(), postResponse.isPublic(),
                "The desired visibility of the created post doesn't match.");
    }

}
