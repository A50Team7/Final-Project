package testcases.ui;

import com.testframework.api.controllers.RestUserController;
import com.testframework.models.User;
import io.restassured.response.Response;

public class ApiHelper {

    public static String getCookieValue(User user) {
        String name = "JSESSIONID";
        Response auth = RestUserController.authUser(user.getUsername(), user.getPassword());
        return auth.getDetailedCookie(name).getValue();
    }

}
