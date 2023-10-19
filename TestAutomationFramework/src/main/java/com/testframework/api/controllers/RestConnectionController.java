package com.testframework.api.controllers;

import com.testframework.api.models.ConnectionRequest;
import com.testframework.api.models.ConnectionResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestConnectionController extends BaseController {

    public static String sendFriendRequest(ConnectionRequest connectionRequest, String cookie) {
        return base(cookie)
                .body(connectionRequest)
                .post("/auth/request")
                .then()
                .assertThat().statusCode(200)
                .extract().response().body().asPrettyString();
    }

    public static ConnectionResponse[] getFriendRequests(int receiverId, String cookie) {
        return base(cookie)
                .when()
                .get("auth/users/" + receiverId + "/request/")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ConnectionResponse[].class);
    }

    public static String acceptFriendRequest(int friendRequestId, int receiverId, String cookie) {
        return base(cookie)
                .queryParam("requestId", friendRequestId)
                .post("/auth/users/" + receiverId + "/request/approve")
                .then()
                .assertThat().statusCode(200)
                .extract().response().body().asPrettyString();
    }


}
