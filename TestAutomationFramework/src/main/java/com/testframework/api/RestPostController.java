package com.testframework.api;

import com.testframework.api.models.Post;
import com.testframework.api.models.Skill;
import io.restassured.http.ContentType;


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

    public static Post createPost(Post post) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(post)
                .when()
                .post("/post/auth/creator")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(Post.class);
    }



}
