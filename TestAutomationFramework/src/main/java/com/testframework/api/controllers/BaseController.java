package com.testframework.api.controllers;

import com.testframework.Utils;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Base controller class for managing API requests and responses.
 */
public abstract class BaseController {
    protected static String cookieName = Utils.getConfigPropertyByKey("auth.cookieName");

    /**
     * @param cookieValue the value of the cookie for authentication
     * @return a RequestSpecification object with the provided cookie value
     */
    protected static RequestSpecification base(String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie(cookieName, cookieValue);
    }

    /**
     * @return a RequestSpecification object without a cookie value
     */
    protected static RequestSpecification base() {
        return given()
                .contentType(ContentType.JSON);
    }

}
