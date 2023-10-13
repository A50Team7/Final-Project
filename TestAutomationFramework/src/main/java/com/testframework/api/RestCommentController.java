package com.testframework.api;
import com.testframework.api.models.Comment;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.given;

public class RestCommentController {

    public static Comment[] getAllCommentsOnPost(int postId) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("postId", postId)
                .get("/comment/byPost")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response().as(Comment[].class);

    }

    public static Comment createComment(Comment comment, String cookie) {
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
                .extract().response().as(Comment.class);
    }
}
