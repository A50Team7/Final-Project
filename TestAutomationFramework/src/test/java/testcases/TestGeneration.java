package testcases;
import com.testframework.Utils;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

public class TestGeneration extends BaseTest {
    @Test
    public void testGeneration() {
        User user = UserFactory.createUser(2, 10, 10);

        RegisterPage registerPage = new RegisterPage(actions.getDriver(), Utils.getConfigPropertyByKey("weare.register.url"));
        actions.getDriver().get(Utils.getConfigPropertyByKey("weare.register.url"));
        registerPage.enterAllDataAndRegister(user);
    }
}
