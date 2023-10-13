package testcases.api;

import com.testframework.api.RestUserController;
import com.testframework.databasehelper.UserHelper;
import com.testframework.api.models.RequestUser;
import com.testframework.api.models.RequestUsers;
import com.testframework.api.models.ResponseUser;
import com.testframework.api.models.ResponseUsers;
import com.testframework.factories.UserFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestUserControllerTests extends BaseApiTest {
    private RequestUser user;
    private Response creationResponse;

    @BeforeEach
    public void setup() {
        user = new RequestUser("ROLE_USER", UserFactory.createUser());
        creationResponse = RestUserController.createUser(user);
    }

    @Test
    public void getUsers() {
        RequestUsers requestUsers = new RequestUsers(0, true,"", "", 100);
        ResponseUsers[] users = RestUserController.getUsers(requestUsers);

        for (ResponseUsers responseUser : users) {
            ResponseUser returnedUser = RestUserController.getUserById(responseUser.getUserId(), "admin");

            Assertions.assertEquals(responseUser.getUsername(), returnedUser.getUsername(), "The username didn't match.");
            Assertions.assertEquals(responseUser.getUserId(), returnedUser.getId(), "The id didn't match.");
        }
    }

    @Test
    public void getUserById() {
        int id = UserHelper.getUserIdByUsername(String.format("'%s'", user.getUsername()));
        ResponseUser returnedUser = RestUserController.getUserById(id, "admin");

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
