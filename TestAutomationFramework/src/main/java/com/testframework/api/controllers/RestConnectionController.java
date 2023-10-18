package com.testframework.api.controllers;

import com.testframework.api.models.ConnectionRequest;
import com.testframework.api.models.ConnectionResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestConnectionController {

    public static String sendFriendRequest(ConnectionRequest connectionRequest, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie)
                .body(connectionRequest)
                .post("/auth/request")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().body().asPrettyString();
    }

    public static ConnectionResponse[] getFriendRequests(int receiverId, String cookie) {
        return given()
                .cookie("JSESSIONID", cookie)
                .when()
                .get("auth/users/" + receiverId + "/request/")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(ConnectionResponse[].class);
    }

    public static Response acceptFriendRequest(int friendRequestId, int senderId, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("requestId", friendRequestId)
                .cookie("JSESSIONID", cookie)
                .post("/auth/users/" + senderId + "/request/approve")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response();
    }


}
