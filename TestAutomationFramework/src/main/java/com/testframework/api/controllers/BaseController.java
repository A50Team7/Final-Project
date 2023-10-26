package com.testframework.api.controllers;

import com.testframework.Utils;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Base controller class for managing API requests and responses.
 */
public class BaseController {
    protected static String cookieName = Utils.getConfigPropertyByKey("auth.cookieName");

    /**
     * Creates a base RequestSpecification object with the provided cookie value.
     *
     * @param cookieValue the value of the cookie for authentication
     * @return a RequestSpecification object with the provided cookie value
     */
    public static RequestSpecification base(String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie(cookieName, cookieValue);
    }

    /**
     * Creates a base RequestSpecification object without a cookie value.
     *
     * @return a RequestSpecification object without a cookie value
     */
    public static RequestSpecification base() {
        return given()
                .contentType(ContentType.JSON);
    }

}
