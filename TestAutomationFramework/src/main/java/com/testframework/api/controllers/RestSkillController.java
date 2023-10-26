package com.testframework.api.controllers;

import com.testframework.api.models.Skill;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Controller class for managing skill-related API requests and responses.
 */
public class RestSkillController extends BaseController {

    /**
     * Retrieves an array of Skill objects.
     *
     * @return an array of Skill objects
     */
    public static Skill[] getAll() {
        return base()
                .get("/skill")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(Skill[].class);
    }

    /**
     * Retrieves a single Skill object based on the provided skill ID and status code.
     *
     * @param skillId    the ID of the skill to retrieve
     * @param statusCode the status code of the request
     * @return the Skill object corresponding to the specified skill ID
     */
    public static Skill getOne(int skillId, int statusCode) {
        return base()
                .queryParam("skillId", skillId)
                .when()
                .get("/skill/getOne")
                .then()
                .assertThat().statusCode(statusCode)
                .extract().response().as(Skill.class);
    }

    /**
     * Retrieves a single Skill object based on the provided skill ID.
     *
     * @param skillId the ID of the skill to retrieve
     * @return the Skill object corresponding to the specified skill ID
     */
    public static Skill getOne(int skillId) {
        return base()
                .queryParam("skillId", skillId)
                .when()
                .get("/skill/getOne")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(Skill.class);
    }

    /**
     * Creates a new skill with the provided Skill object.
     *
     * @param skill the Skill object containing the skill information
     * @return the Skill object corresponding to the created skill
     */
    public static Skill createSkill(Skill skill) {
        return base()
                .body(skill)
                .when()
                .post("/skill/create")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(Skill.class);
    }

    /**
     * Deletes a skill based on the provided skill ID.
     *
     * @param skillId the ID of the skill to delete
     * @return the Response object containing the result of the delete request
     */
    public static Response deleteSkill(int skillId) {
        return base()
                .queryParam("skillId", skillId)
                .when()
                .put("/skill/delete")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    /**
     * Edits a skill based on the provided name and skill ID.
     *
     * @param name    the name of the skill to edit
     * @param skillId the ID of the skill to edit
     * @return the Response object containing the result of the edit request
     */
    public static Response editSkill(String name, int skillId) {
        return base()
                .queryParam("skill", name)
                .queryParam("skillId", skillId)
                .when()
                .put("/skill/edit")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

}
