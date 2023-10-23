package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestConnectionController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.ConnectionRequest;
import com.testframework.api.models.UserRequest;
import com.testframework.databasehelper.ConnectionHelper;
import com.testframework.databasehelper.RequestsHelper;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PersonalProfilePage;

public class ConnectionTests extends BaseTest {

    private boolean connected = false;
    private User receiver;
    private String senderCookieValue;
    private String receiverCookieValue;
    private static String profileUrl = Utils.getConfigPropertyByKey("weare.profile.url");
    public static PersonalProfilePage profilePage;
    private Response senderResponse;
    private Response receiverResponse;
    private int receiverId;
    private int senderId;

    @BeforeEach
    public void setup() {
        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));
        receiver = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", receiver));

        senderCookieValue = login(user);

        senderId = user.getUserId();
        receiverId = receiver.getUserId();

        profilePage = new PersonalProfilePage(actions.getDriver(), String.format(profileUrl, receiverId));
        profilePage.navigateToPage();
        profilePage.sendConnectionRequest();
    }

    @Test
    public void FP_53_sendingFriendRequest_Should_BeSuccessful() {
        profilePage.assertConnectedMessagePresent();
    }

    @Test
    public void FP_54_acceptingFriendRequest_Should_BeSuccessful() {
        actions.cleanDriver("weare.baseUrl");
        receiverCookieValue = login(receiver);
        profilePage.navigateToPage();
        profilePage.viewNewFriendRequests();
        profilePage.acceptLatestRequest();
        connected = true;

        profilePage = new PersonalProfilePage(actions.getDriver(), String.format(profileUrl, senderId));
        profilePage.navigateToPage();
        profilePage.assertDisconnectButtonPresent();
    }

    @Test
    public void FP_55_disconnectingWithAlreadyConnectedUser_Should_BeSuccessful() {
        actions.cleanDriver("weare.baseUrl");
        receiverCookieValue = login(receiver);
        profilePage.navigateToPage();
        profilePage.viewNewFriendRequests();
        profilePage.acceptLatestRequest();
        connected = true;

        profilePage = new PersonalProfilePage(actions.getDriver(), String.format(profileUrl, senderId));
        profilePage.navigateToPage();
        Assertions.assertAll(
                () -> profilePage.assertDisconnectButtonPresent(),
                () -> {
                    profilePage.disconnect();
                    profilePage.assertConnectButtonPresent();
                }
        );
    }

    @AfterEach
    public void deleteSecondUser() {
        if (connected) disconnect();
        RequestsHelper.truncateRequestsTable();
        ConnectionHelper.truncateConnectionsTable();
        UserHelper.deleteUser("username", String.format("'%s'", receiver.getUsername()));
    }

    private void disconnect() {
        ConnectionRequest connectionRequest = new ConnectionRequest(receiver.getUserId(), receiver.getUsername());
        RestConnectionController.sendFriendRequest(connectionRequest, senderCookieValue);
    }
}
