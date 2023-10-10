package testcases.api;

import com.testframework.Utils;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {
    @BeforeAll
    public static void Setup() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        RestAssured.baseURI = Utils.getConfigPropertyByKey("weare.api.url");
    }
}
