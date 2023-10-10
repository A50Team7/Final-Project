package testcases.api;

import com.testframework.api.RestSkillController;
import com.testframework.api.models.ApiSkill;
import com.testframework.generations.GenerateRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class RestSkillControllerTests extends BaseApiTest {

    boolean deleted = false;
    ApiSkill skill;
    ApiSkill createResponse;

    @BeforeEach
    public void setup() {
        skill = new ApiSkill();

        createResponse = RestSkillController.createSkill(skill);

        skill.setSkillId(createResponse.getSkillId());
    }

    @Test
    public void findAll() {
        var skills = RestSkillController.getAll();

        for (ApiSkill skill : skills) {
            var getOneResponse = RestSkillController.getOne(skill.getSkillId());

            Assertions.assertEquals(skill, getOneResponse, "The json body doesn't match the created skill.");
        }
    }

    @Test
    public void getOne() {
        var getOneResponse = RestSkillController.getOne(skill.getSkillId());

        Assertions.assertEquals(skill, getOneResponse, "The json body doesn't match the created skill.");
    }

    @Test
    public void createSkill() {
        Assertions.assertEquals(skill, createResponse, "The json body doesn't match the created skill.");
    }

    @Test
    public void deleteSkill() {
        var deleteResponse = RestSkillController.deleteSkill(skill.getSkillId());

        RestSkillController.getOne(skill.getSkillId(), 404);

        if (deleteResponse.statusCode()==200) deleted = true;
    }

    @Test
    public void editSkill() {
        skill.setSkill(GenerateRandom.generateRandomBoundedAlphabeticString(15));
        RestSkillController.editSkill(skill.getSkill(), skill.getSkillId());

        var getOneResponse = RestSkillController.getOne(skill.getSkillId());
        Assertions.assertEquals(skill, getOneResponse, "The json body doesn't match the created skill.");
    }

    @AfterEach
    public void cleanup() {
        if (deleted) return;

        RestSkillController.deleteSkill(skill.getSkillId());
    }

}
