package com.testframework.api;

import com.testframework.api.models.Post;
import com.testframework.api.models.PostEditor;
import com.testframework.api.models.Skill;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class RestPostController {


    public static Post[] getAllPosts() {
        return given()
                .get("/post/")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(Post[].class);

    }

    public Post createPost(Post post, String cookie) {
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
                .extract().response().as(Post.class);
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



}
