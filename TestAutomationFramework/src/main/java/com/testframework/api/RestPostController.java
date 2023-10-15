package com.testframework.api;

import com.testframework.api.models.RequestPost;
import com.testframework.api.models.PostEditor;
import com.testframework.api.models.ResponsePost;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class RestPostController {


    public static ResponsePost[] getAllPosts() {
        return given()
                .contentType(ContentType.JSON)
                .get("/post/")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(ResponsePost[].class);

    }

    public static ResponsePost createPost(RequestPost post, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookie)
                .body(post)
                .when()
                .post("/post/auth/creator")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(ResponsePost.class);
    }

    public static Response editPost(int postId, PostEditor postEditor, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookie)
                .queryParam("postId", postId)
                .body(postEditor)
                .when()
                .put("/post/auth/editor")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

    }

    public static Response likePost(int postId, String cookie) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .cookie("JSESSIONID", cookie)
                .queryParam("postId", postId)
                .when()
                .post("/post/auth/likesUp")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
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

    public static void deleteAllPosts(String cookieValue) {
        ResponsePost[] posts = getAllPosts();
        for (ResponsePost post : posts) {
            deletePost(post.getPostId(), cookieValue);
        }
    }
}
