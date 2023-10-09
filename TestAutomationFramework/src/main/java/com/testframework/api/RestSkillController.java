package com.testframework.api;

import com.testframework.api.models.ApiSkill;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestSkillController {

    public static ApiSkill[] getAll() {
        return given()
                .get("/skill")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ApiSkill[].class);
    }

    public static ApiSkill getOne(int skillId, int statusCode) {
        return given()
                .queryParam("skillId", skillId)
                .when()
                .get("/skill/getOne")
                .then()
                .assertThat().statusCode(statusCode)
                .extract().response().as(ApiSkill.class);
    }

    public static ApiSkill getOne(int skillId) {
        return given()
                .queryParam("skillId", skillId)
                .when()
                .get("/skill/getOne")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ApiSkill.class);
    }

    public static ApiSkill createSkill(ApiSkill skill) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(skill)
                .when()
                .post("/skill/create")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ApiSkill.class);
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
