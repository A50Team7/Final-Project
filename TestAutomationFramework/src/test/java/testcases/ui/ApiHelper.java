package testcases.ui;

import com.testframework.api.RestPostController;
import com.testframework.api.RestUserController;
import com.testframework.api.models.ResponsePost;
import com.testframework.models.Post;
import com.testframework.models.User;
import io.restassured.response.Response;

import java.util.Arrays;

public class ApiHelper {

    public static String getCookieValue(User user) {
        String name = "JSESSIONID";
        Response auth = RestUserController.authUser(user.getUsername(), user.getPassword());
        return auth.getDetailedCookie(name).getValue();
    }

    public static void deleteCreatedPost(Post post, String cookieValue) {
        ResponsePost[] posts = RestPostController.getAllPosts();
        ResponsePost postToDelete = Arrays.stream(posts).filter(x -> x.getContent().equals(post.getContent())).findAny().orElse(null);
        if (postToDelete == null) {

        }
        RestPostController.deletePost(postToDelete.getPostId(), cookieValue);
    }
}
