package testcases.api;

import com.testframework.api.RestConnectionController;
import com.testframework.api.RestUserController;
import com.testframework.api.models.FriendRequest;
import com.testframework.api.models.RequestUser;
import com.testframework.factories.UserFactory;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;

public class RestConnectionControllerTests extends BaseApiTest{
    private RequestUser sender;
    private Response createdSender;

    private RequestUser receiver;
    private Response createdReceiver;
    private Cookie authCookie;
    @Test
    public void setup() {
        sender = new RequestUser("ROLE_USER", UserFactory.createUser());
        createdSender = RestUserController.createUser(sender);

        receiver = new RequestUser("ROLE_USER", UserFactory.createUser());
        createdReceiver = RestUserController.createUser(receiver);

        String username1 = sender.getUsername();
        String password1 = sender.getPassword();
        Response auth = RestUserController.authUser(username1, password1);
        authCookie = auth.getDetailedCookie("JSESSIONID");

        String username2 = receiver.getUsername();
        String password2 = receiver.getPassword();
        Response auth2 = RestUserController.authUser(username2, password2);
        Cookie authCookie2 = auth2.getDetailedCookie("JSESSIONID");

        ResponseBody receiverBody = createdReceiver.getBody();
        var receiverArray = receiverBody.asString().split(" ");
        var responseReceiverId = receiverArray[6];
        int receiverId = Integer.valueOf(responseReceiverId);

        ResponseBody senderBody = createdSender.getBody();
        var senderArray = senderBody.asString().split(" ");
        var responseSenderId = senderArray[6];
        int senderId = Integer.valueOf(responseSenderId);

        var friendRequest = new FriendRequest(receiverId,receiver.getUsername());
        RestConnectionController.sendFriendRequest(friendRequest, authCookie.getValue());

        var friendRequests = RestConnectionController.showFriendRequests(receiverId, authCookie2.getValue());

        ResponseBody frReqBody = friendRequests.getBody();
        var frReqArray = frReqBody.asString().split(" ");
        var frReqText = frReqArray[0];
        JsonPath jsonPath = new JsonPath(frReqBody.asString());
        String req_id = jsonPath.getString("id").substring(1,3);
        int friendReqId = Integer.parseInt(req_id);

        RestConnectionController.acceptFriendRequest(friendReqId, receiverId, authCookie2.getValue());

    }


}
