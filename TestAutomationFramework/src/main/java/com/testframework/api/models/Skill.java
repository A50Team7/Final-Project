package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.enums.ProfessionalCategory;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Skill {

    public Skill() {
        setSkillId(0);
        setSkill(GenerateRandom.generateRandomBoundedAlphabeticString(15));
        setCategory(new Category(ProfessionalCategory.getProfessionalCategoryById(ProfessionalCategory.selectRandomId())));
    }

    private int skillId;
    private String skill;
    private Category category;

}
