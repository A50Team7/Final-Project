package com.testframework.api;

import com.testframework.Utils;
import com.testframework.api.models.RequestUser;
import com.testframework.api.models.RequestUsers;
import com.testframework.api.models.ResponseUser;
import com.testframework.api.models.ResponseUsers;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestUserController {


    public static Response createUser(RequestUser user) {
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

    public static ResponseUsers[] getUsers(RequestUsers requestUsers) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestUsers)
                .when()
                .post("/users")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseUsers[].class);
    }

    public static ResponseUser getUserById(int id, String principal) {
        return given()
                .queryParam("principal", principal)
                .when()
                .get("/users/auth/" + String.valueOf(id))
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseUser.class);
    }

    public static Response authUser(String username, String password) {
        return given()
                .multiPart("username", username)
                .multiPart("password", password)
                .post(Utils.getConfigPropertyByKey("weare.auth.url"));

    }
}
