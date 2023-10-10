package com.testframework.api;

import com.testframework.api.models.ApiUser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static io.restassured.RestAssured.given;

public class RestUserController {
    public static Response createUser(ApiUser user) {
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
}
