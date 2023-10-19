package testcases.api;

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
    private boolean connected = false;
    private String senderAuthCookie;
    private String receiverAuthCookie;
    private User sender;
    private User receiver;
    private String connectionRequestResponse;

    @BeforeEach
    public void setup() {
        sender = UserFactory.createUserWithProfile();
        receiver = UserFactory.createUserWithProfile();

        UserRequest userRequest1 = new UserRequest("ROLE_USER", sender);
        RestUserController.createUser(userRequest1);
        UserRequest userRequest2 = new UserRequest("ROLE_USER", receiver);
        RestUserController.createUser(userRequest2);

        senderAuthCookie = ApiHelper.getCookieValue(sender);
        ConnectionRequest connectionRequest = new ConnectionRequest(receiver.getUserId(), receiver.getUsername());
        connectionRequestResponse = RestConnectionController.sendFriendRequest(connectionRequest, senderAuthCookie);

    }

    @Test
    public void sendFriendRequest() {
        assertMessageContainsBothUsernames(connectionRequestResponse);
    }

    @Test
    public void getFriendRequests() {
        receiverAuthCookie = ApiHelper.getCookieValue(receiver);
        ConnectionResponse[] friendRequests =
                RestConnectionController.getFriendRequests(receiver.getUserId(), receiverAuthCookie);

        Assertions.assertTrue(friendRequests.length > 0,
                "No friend requests present from the receiver's account.");
    }

    @Test
    public void acceptFriendRequest() {
        receiverAuthCookie = ApiHelper.getCookieValue(receiver);
        ConnectionResponse[] friendRequests =
                RestConnectionController.getFriendRequests(receiver.getUserId(), receiverAuthCookie);

        int requestId = friendRequests[0].getId();

        String acceptResponse = RestConnectionController.acceptFriendRequest(requestId, receiver.getUserId(), receiverAuthCookie);
        connected = true;

        assertMessageContainsBothUsernames(acceptResponse);
        Assertions.assertTrue(acceptResponse.contains("approved"),
                "Response doesn't contain 'approved'.");
    }

    @AfterEach
    public void cleanup() {
        if (connected) disconnect();
        RequestsHelper.truncateRequestsTable();
        ConnectionHelper.truncateConnectionsTable();
        UserHelper.deleteUser("username", String.format("'%s'", sender.getUsername()));
        UserHelper.deleteUser("username", String.format("'%s'", receiver.getUsername()));
    }

    private void assertMessageContainsBothUsernames(String text) {
        Assertions.assertTrue(text.contains(sender.getUsername()),
                "Response message doesn't contain the username of the sender.");
        Assertions.assertTrue(text.contains(receiver.getUsername()),
                "Response message doesn't contain the username of the receiver.");
    }

    private void disconnect() {
        ConnectionRequest connectionRequest = new ConnectionRequest(receiver.getUserId(), receiver.getUsername());
        RestConnectionController.sendFriendRequest(connectionRequest, senderAuthCookie);
    }
}
