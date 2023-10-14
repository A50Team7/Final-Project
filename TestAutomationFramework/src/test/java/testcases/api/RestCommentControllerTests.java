package testcases.api;

import com.testframework.api.RestCommentController;
import com.testframework.api.RestPostController;
import com.testframework.api.RestUserController;
import com.testframework.api.models.*;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestCommentControllerTests extends BaseApiTest {


    private RequestUser user;
    private Response createdUser;
    private Cookie authCookie;
    private RequestPost post;
    private ResponsePost createdPost;
    private RequestComment comment;
    private ResponseComment createdComment;
    private int commentId;

    @BeforeEach
    public void setup() {
        user = new RequestUser("ROLE_USER", UserFactory.createUser());
        Response createdUser = RestUserController.createUser(user);
        String username = user.getUsername();
        String password = user.getPassword();
        Response auth = RestUserController.authUser(username, password);
        authCookie = auth.getDetailedCookie("JSESSIONID");

        post = new RequestPost();
        createdPost = new RestPostController().createPost(post, authCookie.getValue());
        post.setPostId(createdPost.getPostId());

        ResponseBody userBody = createdUser.getBody();
        var myArray = userBody.asString().split(" ");
        var responseId = myArray[6];
        int userId = Integer.valueOf(responseId);

        comment = new RequestComment(userId, createdPost.getPostId());
        createdComment = RestCommentController.createComment(comment, authCookie.getValue());
        commentId = createdComment.getCommentId();
    }

    @Test
    public void getAllCommentsByPost() {
        var comments = RestCommentController.getAllCommentsOnPost(createdPost.getPostId(), authCookie.getValue());
    }

    @Test
    public void createComment() {
        String testValue = RestCommentController.getSingleComment(commentId).getContent();
        Assertions.assertEquals(testValue, createdComment.getContent(), "Comment content does not match.");

    }

    @Test
    public void getOneComment() {
        var singleComment = RestCommentController.getSingleComment(commentId);
        Assertions.assertEquals(singleComment.getContent(), createdComment.getContent(), "Comment does not match search.");

    }

    @Test
    public void editComment() {
        String content = GenerateRandom.generateRandomBoundedAlphanumericString(20);
        RestCommentController.editComment(commentId, content, authCookie.getValue());

    }

    @Test
    public void likeComment() {
        Response like = RestCommentController.likeComment(commentId, authCookie.getValue());
        int likeSize = like.jsonPath().getList("likes").size();
        Assertions.assertTrue(likeSize > 0, "There are no likes on this post.");
    }

    @Test
    public void deleteComment() {
        RestCommentController.deleteComment(commentId, authCookie.getValue());
    }

}


