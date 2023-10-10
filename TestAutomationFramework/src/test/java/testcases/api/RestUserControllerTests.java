package testcases.api;

import com.testframework.api.RestUserController;
import com.testframework.api.UserControllerHelper;
import com.testframework.api.models.ApiUser;
import com.testframework.factories.UserFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestUserControllerTests extends BaseApiTest {
    ApiUser user;
    Response creationResponse;

    @BeforeEach
    public void setup() {
        user = new ApiUser("ROLE_USER", UserFactory.createUser());
        creationResponse = RestUserController.createUser(user);
    }

    @Test
    public void createUser() {
        Assertions.assertTrue(creationResponse.getBody().asPrettyString().contains(user.getUsername()));
    }

    @AfterEach
    public void cleanup() {
        UserControllerHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }
}
