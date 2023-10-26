package testcases.api;

import com.testframework.api.controllers.RestPostController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.*;
import com.testframework.conversions.UserConversion;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.PostFactory;
import com.testframework.factories.ProfileFactory;
import com.testframework.factories.ServicesFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Authority;
import com.testframework.models.enums.Visibility;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class RestUserControllerTests extends BaseApiTest {
    int userId;
    private String cookieValue;
    private int postId = -1;
    private User user;
    private Response createdUser;

    @BeforeEach
    public void setup() {
        user = UserFactory.createUser();
        UserRequest userRequest = new UserRequest(Authority.ROLE_USER.toString(), user);
        createdUser = RestUserController.createUser(userRequest);
        cookieValue = getCookieValue(user);

        userId = user.getUserId();
    }

    @Test
    public void getUsers() {
        UsersRequest usersRequest = new UsersRequest(0, true, "", "", 100);
        UsersResponse[] users = RestUserController.getUsers(usersRequest);

        for (UsersResponse responseUser : users) {
            UserResponse returnedUser = RestUserController.getUserById(responseUser.getUserId(), "admin");

            Assertions.assertEquals(responseUser.getUsername(), returnedUser.getUsername(),
                    "The username didn't match.");
            Assertions.assertEquals(responseUser.getUserId(), returnedUser.getId(),
                    "The id didn't match.");
        }
    }

    @Test
    public void getUserById() {
        getUserByIdAndAssertTheResponse();
    }

    @Test
    public void showProfilePosts() {
        Post post = PostFactory.createPost(user, Visibility.PUBLIC);
        PostRequest postRequest = new PostRequest(post);

        PostResponse postResponse = RestPostController.createPost(postRequest, cookieValue);

        postId = postResponse.getPostId();

        UsersRequest profilePostsRequest = new UsersRequest(0, true, "", "", 10);
        var posts = RestUserController.getAllProfilePosts(profilePostsRequest, user.getUserId(), cookieValue);

        Assertions.assertTrue(Arrays.stream(posts)
                .anyMatch(x -> x.getPostId() == postResponse.getPostId()
                        && Objects.equals(x.getContent(), postResponse.getContent())));
    }

    @Test
    public void upgradeUserExpertiseProfile() {
        user.getProfile().setServices(ServicesFactory.createServices());
        ExpertiseProfileRequest expertiseProfileRequest = new ExpertiseProfileRequest(user);

        RestUserController.upgradeUserExpertiseProfile(user.getUserId(), expertiseProfileRequest, cookieValue);
        getUserByIdAndAssertTheResponse();
    }

    @Test
    public void upgradeUserPersonalProfile() {
        user.setProfile(ProfileFactory.createProfile());
        PersonalProfileRequest personalProfileRequest = new PersonalProfileRequest(user);

        RestUserController.upgradeUserPersonalProfile(userId, personalProfileRequest, cookieValue);
        getUserByIdAndAssertTheResponse();
    }

    @Test
    public void createUser() {
        Assertions.assertTrue(createdUser.getBody().asPrettyString().contains(user.getUsername()));
    }

    @AfterEach
    public void cleanup() {
        if (postId != -1) RestPostController.deletePost(postId, cookieValue);
        UserHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }

    private void getUserByIdAndAssertTheResponse() {
        UserResponse response = RestUserController.getUserById(userId, "admin");

        User returnedUser = UserConversion.convertToUser(response);
        Assertions.assertEquals(user, returnedUser);
    }

}
