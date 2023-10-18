package testcases.api;

import com.testframework.Utils;
import com.testframework.api.controllers.RestConnectionController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.ConnectionRequest;
import com.testframework.api.models.ConnectionResponse;
import com.testframework.api.models.UserRequest;
import com.testframework.databasehelper.ConnectionHelper;
import com.testframework.databasehelper.RequestsHelper;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testcases.ApiHelper;

public class RestConnectionControllerTests extends BaseApiTest {
    private String senderAuthCookie;
    private String receiverAuthCookie;
    private User sender;
    private User receiver;
    private UserRequest userRequest1;
    private UserRequest userRequest2;
    private String connectionRequestResponse;

    @BeforeEach
    public void setup() {
        sender = UserFactory.createUserWithProfile();
        receiver = UserFactory.createUserWithProfile();

        userRequest1 = new UserRequest("ROLE_USER", sender);
        RestUserController.createUser(userRequest1);
        userRequest2 = new UserRequest("ROLE_USER", receiver);
        RestUserController.createUser(userRequest2);

        senderAuthCookie = ApiHelper.getCookieValue(sender);
        ConnectionRequest connectionRequest = new ConnectionRequest(receiver.getUserId(), receiver.getUsername());
        connectionRequestResponse = RestConnectionController.sendFriendRequest(connectionRequest, senderAuthCookie);

    }

    @Test
    public void sendFriendRequest() {
        assertMessageContainsBothUsernames(connectionRequestResponse, sender, receiver);
    }

    // Get Friend Requests returns 500 - WIP
    @Test
    public void getFriendRequests() {
        ConnectionResponse[] friendRequests = RestConnectionController.getFriendRequests(receiver.getUserId(), receiverAuthCookie);

        Assertions.assertTrue(friendRequests.length > 0,
                "No friend requests present from the receiver's account.");
    }

    @Test
    public void acceptFriendRequest() {

    }

    @AfterEach
    public void cleanup() {
        RequestsHelper.truncateRequestsTable();
        ConnectionHelper.truncateConnectionsTable();
        UserHelper.deleteUser("username", String.format("'%s'", sender.getUsername()));
        UserHelper.deleteUser("username", String.format("'%s'", receiver.getUsername()));
    }

    private static void assertMessageContainsBothUsernames(String requestResponse, User sender, User receiver) {
        Assertions.assertTrue(requestResponse.contains(sender.getUsername()),
                "Response message doesn't contain the username of the sender.");
        Assertions.assertTrue(requestResponse.contains(receiver.getUsername()),
                "Response message doesn't contain the username of the receiver.");
    }
}
