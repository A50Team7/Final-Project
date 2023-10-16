package testcases.api;

import com.testframework.api.controllers.RestUserController;
import com.testframework.databasehelper.UserHelper;
import com.testframework.api.models.UserRequest;
import com.testframework.api.models.UsersRequest;
import com.testframework.api.models.UserResponse;
import com.testframework.api.models.UsersResponse;
import com.testframework.factories.UserFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestUserControllerTests extends BaseApiTest {
    private UserRequest user;
    private Response creationResponse;

    @BeforeEach
    public void setup() {
        user = new UserRequest("ROLE_USER", UserFactory.createUser());
        creationResponse = RestUserController.createUser(user);
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
        int id = UserHelper.getUserIdByUsername(String.format("'%s'", user.getUsername()));
        UserResponse returnedUser = RestUserController.getUserById(id, "admin");

        Assertions.assertEquals(user.getUsername(), returnedUser.getUsername(), "The username didn't match.");
        Assertions.assertEquals(user.getEmail(), returnedUser.getEmail(), "The email didn't match.");
    }

    @Test
    public void createUser() {
        Assertions.assertTrue(creationResponse.getBody().asPrettyString().contains(user.getUsername()));
    }

    @AfterEach
    public void cleanup() {
        UserHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }
}
