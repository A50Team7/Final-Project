package com.testframework.api.controllers;

import com.testframework.api.models.CommentRequest;
import com.testframework.api.models.CommentResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

/**
 * Controller class for managing comment-related API requests and responses.
 */
public class RestCommentController extends BaseController {

    /**
     * Retrieves an array of CommentResponse objects representing all comments.
     *
     * @param cookie the cookie value for authentication
     * @return an array of CommentResponse objects representing all comments
     */
    public static CommentResponse[] getAllComments(String cookie) {
        return base(cookie)
                .when()
                .get("/comment")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse[].class);
    }

    /**
     * Retrieves an array of CommentResponse objects representing all comments on a post.
     *
     * @param postId the ID of the post to retrieve comments for
     * @param cookie the cookie value for authentication
     * @return an array of CommentResponse objects representing the comments on the post
     */
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

    /**
     * Creates a comment using the provided CommentRequest object and cookie.
     *
     * @param comment the CommentRequest object containing the comment information
     * @param cookie  the cookie value for authentication
     * @return a CommentResponse object representing the created comment
     */
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

    /**
     * Edits a comment based on the provided comment ID, content, and cookie.
     *
     * @param commentId the ID of the comment to edit
     * @param content   the updated content of the comment
     * @param cookie    the cookie value for authentication
     * @return a Response object representing the edit action
     */
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


    /**
     * Retrieves a single CommentResponse object based on the provided comment ID.
     *
     * @param commentId the ID of the comment to retrieve
     * @return a CommentResponse object representing the retrieved comment
     */
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

    /**
     * Likes/Dislikes a comment based on the provided comment ID and cookie.
     *
     * @param commentId the ID of the comment to like
     * @param cookie    the cookie value for authentication
     * @return a CommentResponse object representing the liked comment
     */
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

    /**
     * Deletes a comment based on the provided comment ID and cookie.
     *
     * @param commentId the ID of the comment to delete
     * @param cookie    the cookie value for authentication
     * @return a Response object representing the delete action
     */
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
