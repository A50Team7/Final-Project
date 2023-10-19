package com.testframework.api.controllers;

import com.testframework.Utils;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseController {
    protected static String cookieName = Utils.getConfigPropertyByKey("auth.cookieName");

    public static RequestSpecification base(String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie(cookieName, cookieValue);
    }

    public static RequestSpecification base() {
        return given()
                .contentType(ContentType.JSON);
    }

}
