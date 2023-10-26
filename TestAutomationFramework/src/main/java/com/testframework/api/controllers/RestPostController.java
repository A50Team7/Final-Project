package com.testframework.api.controllers;

import com.testframework.api.models.CommentResponse;
import com.testframework.api.models.PostRequest;
import com.testframework.api.models.EditPostRequest;
import com.testframework.api.models.PostResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

/**
 * Controller class for managing post-related API requests and responses.
 */
public class RestPostController extends BaseController {

    /**
     * Retrieves an array of PostResponse objects.
     *
     * @param cookieValue the cookie value for authentication
     * @return an array of PostResponse objects
     */
    public static PostResponse[] getAllPosts(String cookieValue) {
        return base(cookieValue)
                .queryParam("sorted", true)
                .get("/post/")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse[].class);
    }

    /**
     * Retrieves an array of CommentResponse objects associated with a post.
     *
     * @param postId      the ID of the post
     * @param cookieValue the cookie value for authentication
     * @return an array of CommentResponse objects associated with the post
     */
    public static CommentResponse[] getAllCommentsUnderPost(int postId, String cookieValue) {
        return base(cookieValue)
                .queryParam("postId", postId)
                .get("/post/Comments")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CommentResponse[].class);
    }

    /**
     * Creates a new post with the provided PostRequest object.
     *
     * @param post        the PostRequest object containing the post information
     * @param cookieValue the cookie value for authentication
     * @return the PostResponse object corresponding to the created post
     */
    public static PostResponse createPost(PostRequest post, String cookieValue) {
        return base(cookieValue)
                .body(post)
                .when()
                .post("/post/auth/creator")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse.class);
    }

    /**
     * Edits a post based on the provided post ID and EditPostRequest object.
     *
     * @param postId      the ID of the post to edit
     * @param postEditor  the EditPostRequest object containing the updated post information
     * @param cookieValue the cookie value for authentication
     * @return the Response object containing the result of the edit request
     */
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

    /**
     * Likes/Dislikes a post based on the provided post ID.
     *
     * @param postId      the ID of the post to like
     * @param cookieValue the cookie value for authentication
     * @return the PostResponse object corresponding to the liked post
     */
    public static PostResponse likePost(int postId, String cookieValue) {
        return base(cookieValue)
                .queryParam("postId", postId)
                .when()
                .post("/post/auth/likesUp")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse.class);
    }

    /**
     * Deletes a post based on the provided post ID.
     *
     * @param postId      the ID of the post to delete
     * @param cookieValue the cookie value for authentication
     * @return the Response object containing the result of the delete request
     */
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
