package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestPostController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.PostRequest;
import com.testframework.api.models.PostResponse;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Visibility;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class AdminTests extends BaseTest {

    private User admin;
    private User user;
    private Response userResponse;
    private PostResponse postResponse;
    private Post post;
    private String cookieValue;
    private int userId;
    private static final String ADMIN_URL = Utils.getConfigPropertyByKey("weare.admin.url");
    private static final By disableButton = By.xpath(Utils.getUIMappingByKey("admin.disableUserButton"));
    private static final By enableButton = By.xpath(Utils.getUIMappingByKey("admin.enableUserButton"));
    private static final By editProfileButton = By.xpath(Utils.getUIMappingByKey("admin.editProfileButton"));
    @BeforeEach
    public void setup() {

        admin = UserFactory.createUser();
        String adminName = String.format("admin"+"%s", GenerateRandom.generateRandomBoundedAlphabeticString(5));
        admin.setUsername(adminName);
        RestUserController.createUser(new UserRequest("ROLE_USER", admin));

        user = UserFactory.createUser();
        userResponse = RestUserController.createUser(new UserRequest("ROLE_USER", user));

        ResponseBody userBody = userResponse.getBody();
        var userArray = userBody.asString().split(" ");
        var userResponseId = userArray[6];
        userId = Integer.valueOf(userResponseId);

    }
    @Test
    public void navigatedToAdminPage() {
        cookieValue = login(admin);

        actions.getDriver().get(ADMIN_URL);

        String actualUrl = actions.getDriver().getCurrentUrl();
        Assertions.assertEquals(ADMIN_URL, actualUrl, "Could not navigate to admin page");
    }
    @Test
    public void disableUser() {
        //Login as admin
        cookieValue = login(admin);
        //Navigate to user profile
        String userUrl = String.format("http://localhost:8081/auth/users/%s/profile", userId);
        actions.getDriver().get(userUrl);
        //Click Disable button
        actions.waitForElementClickable(disableButton);
        actions.clickElement(disableButton);
        //Assertions
    }
    @Test
    public void enableUser() {
        //Login as admin
        cookieValue = login(admin);
        //Navigate to user profile
        String userUrl = String.format("http://localhost:8081/auth/users/%s/profile", userId);
        actions.getDriver().get(userUrl);
        //Click Disable button
        actions.waitForElementClickable(disableButton);
        actions.clickElement(disableButton);
        //Click Enable button
        actions.waitForElementClickable(enableButton);
        actions.clickElement(enableButton);
        //Assertions
    }
    @Test
    public void showAllUsers() {

    }
    @Test
    public void editUser() {
        //Login as admin
        cookieValue = login(admin);
        //Navigate to user profile
        String userUrl = String.format("http://localhost:8081/auth/users/%s/profile", userId);
        actions.getDriver().get(userUrl);
        //Edit profile
        actions.waitForElementClickable(editProfileButton);
        actions.clickElement(editProfileButton);
        //Change some data
        //Assert
    }
    @Test
    public void editPost() {
        //Login as User
        cookieValue = login(user);
        //Create a Post
        var postReq = new PostRequest();

        postResponse = RestPostController.createPost(postReq, cookieValue);

        //Login as Admin
        cookieValue = login(admin);
        actions.getDriver().get(ADMIN_URL);
        //Navigate to Post
        String postUrl = String.format("http://localhost:8081/posts/%s", postResponse.getPostId());
        actions.getDriver().get(postUrl);
        //Edit Post

    }
    @Test
    public void deletePost() {
        //Login as User
        //Create a Post
        //Login as Admin
        //Navigate to Post
        //Delete Post
    }
    @Test
    public void editComment() {
        //Login as User
        //Create a Post
        //Create a Comment
        //Login as Admin
        //Navigate to Post
        //Edit Comment
    }
    @Test
    public void deleteComment() {
        //Login as User
        //Create a Post
        //Create a Comment
        //Login as Admin
        //Navigate to Post
        //Delete Comment
    }
}
