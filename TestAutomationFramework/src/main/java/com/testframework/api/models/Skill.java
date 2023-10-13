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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill c = (Skill) o;

        return skillId == c.skillId
                && skill.equals(c.skill)
                && category.equals(c.category);
    }

}
