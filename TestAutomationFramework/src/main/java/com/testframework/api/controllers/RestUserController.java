package com.testframework.api.controllers;

import com.testframework.Utils;
import com.testframework.api.models.*;
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
                .get("/users/auth/" + id)
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(UserResponse.class);
    }

    public static ExpertiseProfileResponse upgradeUserExpertiseProfile(int userId, ExpertiseProfileRequest request, String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(request)
                .cookie("JSESSIONID", cookieValue)
                .when()
                .post(String.format("/users/auth/%s/expertise", userId))
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ExpertiseProfileResponse.class);
    }

    public static PersonalProfileResponse upgradeUserPersonalProfile(int userId, PersonalProfileRequest request, String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(request)
                .cookie("JSESSIONID", cookieValue)
                .when()
                .post("/users/auth/" + userId + "/personal")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PersonalProfileResponse.class);
    }

    public static PostResponse[] getAllProfilePosts(UsersRequest request, int userId, String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(request)
                .cookie("JSESSIONID", cookieValue)
                .when()
                .get(String.format("/users/%s/posts", userId))
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse[].class);
    }

    public static Response authUser(String username, String password) {
        return given()
                .multiPart("username", username)
                .multiPart("password", password)
                .post(Utils.getConfigPropertyByKey("weare.auth.url"));

    }

}
