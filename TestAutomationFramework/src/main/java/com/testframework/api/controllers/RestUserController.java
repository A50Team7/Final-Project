package com.testframework.api.controllers;

import com.testframework.Utils;
import com.testframework.api.models.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Controller class for managing user-related API requests and responses.
 */
public class RestUserController extends BaseController {

    /**
     * Creates a new user with the provided user information.
     *
     * @param user the UserRequest object containing user information
     * @return the Response object containing the result of the POST request
     */
    public static Response createUser(UserRequest user) {
        return base()
                .body(user)
                .when()
                .post("/users/")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    /**
     * Retrieves an array of UsersResponse objects based on the provided request.
     *
     * @param usersRequest the UsersRequest object containing request information
     * @return an array of UsersResponse objects
     */
    public static UsersResponse[] getUsers(UsersRequest usersRequest) {
        return base()
                .body(usersRequest)
                .when()
                .post("/users")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(UsersResponse[].class);
    }

    /**
     * Retrieves a UserResponse object based on the provided user ID and principal.
     *
     * @param id        the ID of the user to retrieve
     * @param principal the principal value for the request
     * @return the UserResponse object corresponding to the specified ID
     */
    public static UserResponse getUserById(int id, String principal) {
        return base()
                .queryParam("principal", principal)
                .when()
                .get("/users/auth/" + id)
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(UserResponse.class);
    }

    /**
     * Upgrades the expertise profile of a user with the provided user ID and request information.
     *
     * @param userId      the ID of the user whose expertise profile is being upgraded
     * @param request     the ExpertiseProfileRequest object containing the upgrade information
     * @param cookieValue the cookie value to be used for the request
     * @return the ExpertiseProfileResponse object containing the result of the upgrade request
     */
    public static ExpertiseProfileResponse upgradeUserExpertiseProfile(int userId, ExpertiseProfileRequest request, String cookieValue) {
        return base(cookieValue)
                .body(request)
                .when()
                .post(String.format("/users/auth/%s/expertise", userId))
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ExpertiseProfileResponse.class);
    }

    /**
     * Upgrades the personal profile of a user with the provided user ID and request information.
     *
     * @param userId      the ID of the user whose personal profile is being upgraded
     * @param request     the PersonalProfileRequest object containing the upgrade information
     * @param cookieValue the cookie value to be used for the request
     * @return the PersonalProfileResponse object containing the result of the upgrade request
     */
    public static PersonalProfileResponse upgradeUserPersonalProfile(int userId, PersonalProfileRequest request, String cookieValue) {
        return base(cookieValue)
                .body(request)
                .when()
                .post("/users/auth/" + userId + "/personal")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PersonalProfileResponse.class);
    }

    /**
     * Retrieves an array of PostResponse objects for the specified user based on the request and cookie value.
     *
     * @param request     the UsersRequest object containing the request information
     * @param userId      the ID of the user whose profile posts are being retrieved
     * @param cookieValue the cookie value to be used for the request
     * @return an array of PostResponse objects corresponding to the user's profile posts
     */
    public static PostResponse[] getAllProfilePosts(UsersRequest request, int userId, String cookieValue) {
        return base(cookieValue)
                .body(request)
                .when()
                .get(String.format("/users/%s/posts", userId))
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(PostResponse[].class);
    }


    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username the username of the user to authenticate
     * @param password the password of the user to authenticate
     * @return the Response object containing the result of the authentication request
     */
    public static Response authUser(String username, String password) {
        return given()
                .multiPart("username", username)
                .multiPart("password", password)
                .post(Utils.getConfigPropertyByKey("weare.auth.url"));

    }

}
