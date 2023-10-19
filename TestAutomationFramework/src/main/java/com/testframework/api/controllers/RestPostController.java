package com.testframework.api.controllers;

import com.testframework.api.models.CommentResponse;
import com.testframework.api.models.PostRequest;
import com.testframework.api.models.EditPostRequest;
import com.testframework.api.models.PostResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class RestPostController extends BaseController {


    public static PostResponse[] getAllPosts(String cookieValue) {
        return base(cookieValue)
                .queryParam("sorted", true)
                .get("/post/")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse[].class);
    }

    public static CommentResponse[] getAllCommentsUnderPost(int postId, String cookieValue) {
        return base(cookieValue)
                .queryParam("postId", postId)
                .get("/post/Comments")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse[].class);
    }

    public static PostResponse createPost(PostRequest post, String cookieValue) {
        return base(cookieValue)
                .body(post)
                .when()
                .post("/post/auth/creator")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse.class);
    }

    public static Response editPost(int postId, EditPostRequest postEditor, String cookieValue) {
        return base(cookieValue)
                .queryParam("postId", postId)
                .body(postEditor)
                .when()
                .put("/post/auth/editor")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

    }

    public static PostResponse likePost(int postId, String cookieValue) {
        return base(cookieValue)
                .queryParam("postId", postId)
                .when()
                .post("/post/auth/likesUp")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse.class);
    }

    public static Response deletePost(int postId, String cookieValue) {
        return base(cookieValue)
                .queryParam("postId", postId)
                .when()
                .delete("/post/auth/manager")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

}
