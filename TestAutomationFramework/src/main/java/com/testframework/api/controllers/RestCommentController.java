package com.testframework.api.controllers;

import com.testframework.api.models.CommentRequest;
import com.testframework.api.models.CommentResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class RestCommentController extends BaseController{

    public static CommentResponse[] getAllComments(String cookie) {
        return base(cookie)
                .when()
                .get("/comment")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse[].class);
    }

    public static CommentResponse[] getAllCommentsOnPost(int postId, String cookie) {
        return base(cookie)
                .and()
                .queryParam("postId", postId)
                .when()
                .get("/comment/byPost")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse[].class);
    }

    public static CommentResponse createComment(CommentRequest comment, String cookie) {
        return base(cookie)
                .and()
                .body(comment)
                .when()
                .post("/comment/auth/creator")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse.class);
    }

    public static Response editComment(int commentId, String content, String cookie) {
        return base(cookie)
                .and()
                .queryParam("commentId", commentId)
                .and()
                .queryParam("content", content)
                .when()
                .put("/comment/auth/editor")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    public static CommentResponse getSingleComment(int commentId) {
        return base()
                .and()
                .queryParam("commentId", commentId)
                .when()
                .get("/comment/single")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse.class);
    }

    public static CommentResponse likeComment(int commentId, String cookie) {
        return base(cookie)
                .and()
                .queryParam("commentId", commentId)
                .when()
                .post("/comment/auth/likesUp")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse.class);
    }

    public static Response deleteComment(int commentId, String cookie) {
        return base(cookie)
                .and()
                .queryParam("commentId", commentId)
                .when()
                .delete("/comment/auth/manager")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }
}
