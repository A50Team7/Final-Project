package testcases.api;

import com.testframework.api.RestPostController;
import com.testframework.api.models.Post;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import com.testframework.api.RestUserController;
import com.testframework.api.UserControllerHelper;
import com.testframework.api.models.RequestUser;

import static io.restassured.RestAssured.given;

public class RestPostControllerTests extends BaseApiTest {
    RequestUser user;
    private Cookie authCookie;
    private Post post;
    @BeforeEach
    public void setup() {
        user = new RequestUser("ROLE_USER", UserFactory.createUser());

        Response creationResponse = RestUserController.createUser(user);
        String username = user.getUsername();
        String password = user.getPassword();


        Response auth = RestUserController.authUser(username, password)
                .baseUri("http://localhost:8081/authenticate")
                .when()
                .post();
        authCookie = auth.getDetailedCookie("JSESSIONID");


    }

    @AfterEach
    public void cleanup() {
        UserControllerHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }

    @Test
    public void findAllPublicPosts() {
        var posts = RestPostController.getAllPosts();

//        for (Post post : posts) {
//            var getOneResponse = RestPostController.getOne(post.getPostId());
//
//            Assertions.assertEquals(post, getOneResponse, "The json body doesn't match the created post.");
//        }
    }

    @Test
    public void createPost() {

        post = new Post();

        Post response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                .body(post)
                .when()
                .post("/post/auth/creator")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(Post.class);

        //var post = RestPostController.createPost(new Post());
    }
}
