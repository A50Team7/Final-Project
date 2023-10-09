package testcases.api;

import com.testframework.api.models.ApiUser;
import com.testframework.factories.UserFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestUserControllerTests extends BaseApiTest {

    @Test
    public void createUser() {
        ApiUser user = new ApiUser("ROLE_USER", UserFactory.createUser());

        var response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(user)
                .when()
                .post("/users/")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        Assertions.assertTrue(response.getBody().asPrettyString().contains(user.getUsername()));
    }

}
