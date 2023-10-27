package testcases.api;

import com.testframework.api.controllers.RestSkillController;
import com.testframework.api.models.Skill;
import com.testframework.factories.ServicesFactory;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.User;
import com.testframework.models.enums.ProfessionalCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestSkillControllerTests extends BaseApiTest {

    private boolean deleted = false;
    private String skillName = ServicesFactory.generateService();
    private ProfessionalCategory category = UserFactory.selectCategory();
    private Skill skill;
    private Skill createResponse;
    private static final String ERROR = "The created skill doesn't match the expected skill.";


    @BeforeEach
    public void setup() {
        skill = new Skill(skillName, category);

        createResponse = RestSkillController.createSkill(skill);

        skill.setSkillId(createResponse.getSkillId());
    }

    @Test
    public void findAll() {
        var skills = RestSkillController.getAll();

        for (Skill skill : skills) {
            var getOneResponse = RestSkillController.getOne(skill.getSkillId());

            Assertions.assertEquals(skill, getOneResponse, ERROR);
        }
    }

    @Test
    public void getOne() {
        var getOneResponse = RestSkillController.getOne(skill.getSkillId());

        Assertions.assertEquals(skill, getOneResponse, ERROR);
    }

    @Test
    public void createSkill() {
        Assertions.assertEquals(skill, createResponse, ERROR);
    }

    @Test
    public void deleteSkill() {
        var deleteResponse = RestSkillController.deleteSkill(skill.getSkillId());

        RestSkillController.getOne(skill.getSkillId(), 404);

        if (deleteResponse.statusCode() == 200) deleted = true;
    }

    @Test
    public void editSkill() {
        skill.setSkill(GenerateRandom.generateRandomBoundedAlphabeticString(15));
        RestSkillController.editSkill(skill.getSkill(), skill.getSkillId());

        var getOneResponse = RestSkillController.getOne(skill.getSkillId());
        Assertions.assertEquals(skill, getOneResponse, ERROR);
    }

    @AfterEach
    public void cleanup() {
        if (deleted) return;
        RestSkillController.deleteSkill(skill.getSkillId());
    }

}
