package com.testframework.api.controllers;
import com.testframework.api.models.CommentRequest;
import com.testframework.api.models.CommentResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class RestCommentController {

    public static CommentRequest[] getAllCommentsOnPost(int postId, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie)
                .queryParam("postId", postId)
                .get("/comment/byPost")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(CommentRequest[].class);

    }

    public static CommentResponse createComment(CommentRequest comment, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookie)
                .body(comment)
                .when()
                .post("/comment/auth/creator")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse.class);
    }

    public static Response editComment(int commentId, String content , String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookie)
                .queryParam("commentId", commentId)
                .queryParam("content", content)
                .when()
                .put("/comment/auth/editor")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response();
    }

    public static CommentResponse getSingleComment(int commentId) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .queryParam("commentId", commentId)
                .when()
                .get("/comment/single")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse.class);
    }

    public static Response likeComment(int commentId, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookie)
                .queryParam("commentId", commentId)
                .when()
                .post("/comment/auth/likesUp")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response();
    }

    public static Response deleteComment(int commentId, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookie)
                .queryParam("commentId", commentId)
                .when()
                .delete("/comment/auth/manager")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response();
    }
}
