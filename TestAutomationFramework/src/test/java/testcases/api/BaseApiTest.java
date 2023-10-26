package testcases.api;

import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.models.User;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {
    @BeforeAll
    public static void Setup() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        RestAssured.baseURI = Utils.getConfigPropertyByKey("weare.api.url");
    }

    /**
     * Retrieves the value of the cookie for the specified user using API.
     *
     * @param user the user for whom the cookie value is to be retrieved
     * @return the value of the cookie associated with the user
     */
    public static String getCookieValue(User user) {
        String name = Utils.getConfigPropertyByKey("auth.cookieName");
        Response auth = RestUserController.authUser(user.getUsername(), user.getPassword());
        return auth.getDetailedCookie(name).getValue();
    }
}
