package testcases.ui;

import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import org.junit.jupiter.api.BeforeEach;

public class SearchTests extends BaseTest {
    private User user;
    private String cookieValue;
    @BeforeEach
    public void setup() {

        actions.getDriver().get("weare.baseUrl");
        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));

        cookieValue = login(user);

    }





}
