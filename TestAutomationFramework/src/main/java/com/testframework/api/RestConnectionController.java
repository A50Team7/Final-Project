package com.testframework.api;

import com.testframework.api.models.FriendRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestConnectionController {

    public static Response sendFriendRequest(FriendRequest friendRequest, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie)
                .body(friendRequest)
                .post("/auth/request")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response();
    }

    public static Response showFriendRequests(int userId, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie)
                .when()
                .get("auth/users/"+ userId +"/request/")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response();
    }

    public static Response acceptFriendRequest(int friendRequestId, int senderId, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("requestId", friendRequestId)
                .cookie("JSESSIONID", cookie)
                .post("/auth/users/"+ senderId +"/request/approve")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response();
    }




}
