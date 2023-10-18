package com.testframework.api.controllers;

import com.testframework.api.models.PostRequest;
import com.testframework.api.models.EditPostRequest;
import com.testframework.api.models.PostResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class RestPostController {


    public static PostResponse[] getAllPosts(String cookieValue) {
        return given()
                .cookie("JSESSIONID", cookieValue)
                .queryParam("sorted", true)
                .get("/post/")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse[].class);
    }

    public static PostResponse createPost(PostRequest post, String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookieValue)
                .body(post)
                .when()
                .post("/post/auth/creator")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse.class);
    }

    public static Response editPost(int postId, EditPostRequest postEditor, String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookieValue)
                .queryParam("postId", postId)
                .body(postEditor)
                .when()
                .put("/post/auth/editor")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

    }

    public static PostResponse likePost(int postId, String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookieValue)
                .queryParam("postId", postId)
                .when()
                .post("/post/auth/likesUp")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse.class);
    }

    public static Response deletePost(int postId, String cookieValue) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookieValue)
                .queryParam("postId", postId)
                .when()
                .delete("/post/auth/manager")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

}
