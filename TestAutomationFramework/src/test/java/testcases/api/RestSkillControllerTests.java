package testcases.api;

import com.testframework.api.ApiSkill;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class RestSkillControllerTests extends BaseApiTest {

    boolean deleted = false;
    ApiSkill skill;
    ApiSkill createResponse;

    @BeforeEach
    public void setup() {
        skill = new ApiSkill();

        createResponse = given()
                .contentType(ContentType.JSON)
                .and()
                .body(skill)
                .when()
                .post("/skill/create")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ApiSkill.class);

        skill.setSkillId(createResponse.getSkillId());
    }

    @Test
    public void findAll() {
        var skills = given()
                .get("/skill")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ApiSkill[].class);

        for (ApiSkill skill : skills) {
            var getOneResponse = given()
                    .queryParam("skillId", skill.getSkillId())
                    .when()
                    .get("/skill/getOne")
                    .then()
                    .assertThat().statusCode(200)
                    .extract().response().as(ApiSkill.class);

            Assertions.assertEquals(skill, getOneResponse);
        }
    }

    @Test
    public void getOne() {
        var getOneResponse = given()
                .queryParam("skillId", skill.getSkillId())
                .when()
                .get("/skill/getOne")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ApiSkill.class);

        Assertions.assertEquals(skill, getOneResponse);
    }

    @Test
    public void createSkill() {
        Assertions.assertEquals(skill, createResponse);
    }

    @Test
    public void deleteSkill() {
        var deleteResponse = given()
                .queryParam("skillId", skill.getSkillId())
                .when()
                .put("/skill/delete")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        given()
                .queryParam("skillId", skill.getSkillId())
                .when()
                .get("/skill/getOne")
                .then()
                .assertThat().statusCode(404)
                .extract().response().as(ApiSkill.class);

        if (deleteResponse.statusCode()==200) deleted = true;
    }

    @AfterEach
    public void cleanup() {
        if (deleted) return;

        given().queryParam("skillId", skill.getSkillId()).when().put("/skill/delete");
    }
}
