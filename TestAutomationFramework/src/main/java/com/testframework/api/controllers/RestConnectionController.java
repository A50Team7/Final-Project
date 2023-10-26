package com.testframework.api.controllers;

import com.testframework.api.models.ConnectionRequest;
import com.testframework.api.models.ConnectionResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Controller class for managing connection-related API requests and responses.
 */
public class RestConnectionController extends BaseController {

    /**
     * Sends a friend request using the provided ConnectionRequest object and cookie.
     * The same HTTP request is used for removing one from connections.
     *
     * @param connectionRequest the ConnectionRequest object containing the request information
     * @param cookie            the cookie value for authentication
     * @return a string representation of the sent friend request
     */
    public static String sendFriendRequest(ConnectionRequest connectionRequest, String cookie) {
        return base(cookie)
                .body(connectionRequest)
                .post("/auth/request")
                .then()
                .assertThat().statusCode(200)
                .extract().response().body().asPrettyString();
    }

    /**
     * Retrieves an array of ConnectionResponse objects representing friend requests for a user.
     *
     * @param receiverId the ID of the user receiving the friend requests
     * @param cookie     the cookie value for authentication
     * @return an array of ConnectionResponse objects representing the friend requests
     */
    public static ConnectionResponse[] getFriendRequests(int receiverId, String cookie) {
        return base(cookie)
                .when()
                .get("auth/users/" + receiverId + "/request/")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ConnectionResponse[].class);
    }

    /**
     * Accepts a friend request based on the provided friend request ID, receiver ID, and cookie.
     *
     * @param friendRequestId the ID of the friend request to accept
     * @param receiverId      the ID of the user receiving the friend request
     * @param cookie          the cookie value for authentication
     * @return a string representation of the accepted friend request
     */
    public static String acceptFriendRequest(int friendRequestId, int receiverId, String cookie) {
        return base(cookie)
                .queryParam("requestId", friendRequestId)
                .post("/auth/users/" + receiverId + "/request/approve")
                .then()
                .assertThat().statusCode(200)
                .extract().response().body().asPrettyString();
    }


}
