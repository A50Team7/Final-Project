package testcases.api;

import com.testframework.api.controllers.RestPostController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.*;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Visibility;
import io.restassured.response.Response;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testcases.ApiHelper;

import java.util.Arrays;
import java.util.Objects;

public class RestUserControllerTests extends BaseApiTest {
    private String cookieValue;
    private int postId = -1;
    private User user;
    private UserRequest userRequest;
    private Response creationResponse;

    @BeforeEach
    public void setup() {
        user = UserFactory.createUserWithProfile();
        userRequest = new UserRequest("ROLE_USER", user);
        creationResponse = RestUserController.createUser(userRequest);
        cookieValue = ApiHelper.getCookieValue(user);
    }

    @Test
    public void getUsers() {
        UsersRequest usersRequest = new UsersRequest(0, true,"", "", 100);
        UsersResponse[] users = RestUserController.getUsers(usersRequest);

        for (UsersResponse responseUser : users) {
            UserResponse returnedUser = RestUserController.getUserById(responseUser.getUserId(), "admin");

            Assertions.assertEquals(responseUser.getUsername(), returnedUser.getUsername(), "The username didn't match.");
            Assertions.assertEquals(responseUser.getUserId(), returnedUser.getId(), "The id didn't match.");
        }
    }

    @Test
    public void getUserById() {
        int id = UserHelper.getUserIdByUsername(String.format("'%s'", userRequest.getUsername()));
        UserResponse returnedUser = RestUserController.getUserById(id, "admin");

        Assertions.assertEquals(userRequest.getUsername(), returnedUser.getUsername(), "The username didn't match.");
        Assertions.assertEquals(userRequest.getEmail(), returnedUser.getEmail(), "The email didn't match.");
    }

    @Test
    public void showProfilePosts() {
        Post post = PostFactory.createPost(user, Visibility.PUBLIC);
        PostRequest postRequest = new PostRequest(post);

        PostResponse postResponse = RestPostController.createPost(postRequest, cookieValue);

        postId = postResponse.getPostId();

        UsersRequest profilePostsRequest = new UsersRequest(0, true,"", "", 10);
        var posts = RestUserController.getAllProfilePosts(profilePostsRequest, user.getUserId(), cookieValue);

        Assertions.assertTrue(Arrays.stream(posts)
                .anyMatch(x -> x.getPostId()==postResponse.getPostId()
                        && Objects.equals(x.getContent(), postResponse.getContent())));
    }

    @Test
    public void upgradeUserExpertiseProfile() {
        ExpertiseProfileRequest expertiseProfileRequest = new ExpertiseProfileRequest(user);

        var response = RestUserController.upgradeUserExpertiseProfile(user.getUserId(), expertiseProfileRequest, cookieValue);

        Assertions.assertEquals(expertiseProfileRequest.getAvailability(), response.getAvailability(), "Availability doesn't match");
        Assertions.assertEquals(expertiseProfileRequest.getCategory(), response.getCategory(), "Category doesn't match");
        Assertions.assertEquals(expertiseProfileRequest.getSkill1(), response.getSkills().get(0).getSkill(), "Skill 1 doesn't match");
    }

    @Test
    public void upgradeUserPersonalProfile() {
        user.getProfile().setGender(Gender.MALE);
        PersonalProfileRequest personalProfileRequest = new PersonalProfileRequest(user);

        var response = RestUserController.upgradeUserPersonalProfile(user.getUserId(), personalProfileRequest, cookieValue);

        Assertions.assertEquals(personalProfileRequest.getFirstName(), response.getFirstName(), "First name doesn't match");
        Assertions.assertEquals(personalProfileRequest.getLastName(), response.getLastName(), "Last name doesn't match");
    }

    @Test
    public void createUser() {
        Assertions.assertTrue(creationResponse.getBody().asPrettyString().contains(userRequest.getUsername()));
    }

    @AfterEach
    public void cleanup() {
        if (postId!=-1) RestPostController.deletePost(postId, cookieValue);
        UserHelper.deleteUser("username", String.format("'%s'", userRequest.getUsername()));
    }
}
