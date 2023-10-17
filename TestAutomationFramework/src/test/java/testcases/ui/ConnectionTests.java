package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ConnectionTests extends BaseTest{

    private User sender;
    private User receiver;
    private String cookieValue;
    private static String profileUrl = Utils.getConfigPropertyByKey("weare.profile.url");
    private static final By connectButton = By.xpath(Utils.getUIMappingByKey("connection.connectButton"));
    private final static String BASE_URL = Utils.getConfigPropertyByKey("weare.baseUrl");
    private Response senderResponse;
    private Response receiverResponse;
    private int receiverId;
    private int senderId;
    @BeforeEach
    public void setup() {
        //Create 2 users
        sender = UserFactory.createUser();
        senderResponse = RestUserController.createUser(new UserRequest("ROLE_USER", sender));
        receiver = UserFactory.createUser();
        receiverResponse = RestUserController.createUser(new UserRequest("ROLE_USER", receiver));

        cookieValue = login(sender);

        ResponseBody senderBody = senderResponse.getBody();
        var senderArray = senderBody.asString().split(" ");
        var senderResponseId = senderArray[6];
        senderId = Integer.valueOf(senderResponseId);

        ResponseBody receiverBody = receiverResponse.getBody();
        var receiverArray = receiverBody.asString().split(" ");
        var receiverResponseId = receiverArray[6];
        receiverId = Integer.valueOf(receiverResponseId);

       // actions.getDriver().get(BASE_URL);
    }
    @AfterEach
    public void cleaner() {
        //Checkup the database



    }
    @Test
    public void sendFriendRequest() {
        //Navigate to Receiver
        //Click Connect Button

        String receiverProfileUrl = String.format(profileUrl,receiverId);
        actions.getDriver().get(receiverProfileUrl);

        actions.waitForElementClickable(connectButton);
        actions.clickElement(connectButton);
        //Assertions


    }
    @Test
    public void showFriendRequests() {
        //-> Send request to user2
        //user2 login -> Open friend requests list

    }
    @Test
    public void acceptFriendRequest() {
        //-> Send request to user2
        //user2 login -> Open friend requests list
        //user2 -> accept friend request
    }
}
