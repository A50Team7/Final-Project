package com.testframework.api.controllers;

import com.testframework.api.models.Skill;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestSkillController {

    public static Skill[] getAll() {
        return given()
                .get("/skill")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(Skill[].class);
    }

    public static Skill getOne(int skillId, int statusCode) {
        return given()
                .queryParam("skillId", skillId)
                .when()
                .get("/skill/getOne")
                .then()
                .assertThat().statusCode(statusCode)
                .extract().response().as(Skill.class);
    }

    public static Skill getOne(int skillId) {
        return given()
                .queryParam("skillId", skillId)
                .when()
                .get("/skill/getOne")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(Skill.class);
    }

    public static Skill createSkill(Skill skill) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(skill)
                .when()
                .post("/skill/create")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(Skill.class);
    }

    public static Response deleteSkill(int skillId) {
        return given()
                .queryParam("skillId", skillId)
                .when()
                .put("/skill/delete")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    public static Response editSkill(String name, int skillId) {
        return given()
                .queryParam("skill", name)
                .queryParam("skillId", skillId)
                .when()
                .put("/skill/edit")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

}
