package testcases.api;

import com.testframework.api.RestCommentController;
import com.testframework.api.RestPostController;
import com.testframework.api.RestUserController;
import com.testframework.api.models.Comment;
import com.testframework.api.models.Post;
import com.testframework.api.models.RequestUser;
import com.testframework.factories.UserFactory;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestCommentControllerTests extends BaseApiTest {


    private RequestUser user;
    private Response createdUser;
    private Cookie authCookie;
    private Post post;
    private Post createdPost;
    private Comment createdComment;

    @BeforeEach
    public void setup() {
        user = new RequestUser("ROLE_USER", UserFactory.createUser());

        createdUser = RestUserController.createUser(user);
        String username = user.getUsername();
        String password = user.getPassword();

        Response auth = RestUserController.authUser(username, password);

        authCookie = auth.getDetailedCookie("JSESSIONID");
        post = new Post();
        createdPost = new RestPostController().createPost(post, authCookie.getValue());
        post.setPostId(createdPost.getPostId());
        ResponseBody body = createdUser.getBody();
        System.out.println(body.asString());
        var myArray = body.asString().split(" ");
        var responseId = myArray[6];
        int userId = Integer.valueOf(responseId);

        Comment comment = new Comment(userId, createdPost.getPostId());
        createdComment = RestCommentController.createComment(comment, authCookie.getValue());





    }

    @Test
    public void getAllCommentsByPost() {
        var comments = RestCommentController.getAllCommentsOnPost(81);
    }

    @Test
    public void createComment() {


//        int userId = UserControllerHelper.getUserIdByUsername(user.getUsername());
//        int postId = createdPost.getPostId();
//        Comment comment = new Comment(responseId, postId);
       // createdComment = RestCommentController.createComment(comment, authCookie.getValue());


    }

}
