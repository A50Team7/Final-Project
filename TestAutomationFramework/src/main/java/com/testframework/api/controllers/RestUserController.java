package com.testframework.api.controllers;

import com.testframework.Utils;
import com.testframework.api.models.UserRequest;
import com.testframework.api.models.UsersRequest;
import com.testframework.api.models.UserResponse;
import com.testframework.api.models.UsersResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestUserController {


    public static Response createUser(UserRequest user) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(user)
                .when()
                .post("/users/")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    public static UsersResponse[] getUsers(UsersRequest usersRequest) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(usersRequest)
                .when()
                .post("/users")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(UsersResponse[].class);
    }

    public static UserResponse getUserById(int id, String principal) {
        return given()
                .queryParam("principal", principal)
                .when()
                .get("/users/auth/" + String.valueOf(id))
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(UserResponse.class);
    }

    public static Response authUser(String username, String password) {
        return given()
                .multiPart("username", username)
                .multiPart("password", password)
                .post(Utils.getConfigPropertyByKey("weare.auth.url"));

    }
}
