package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.enums.ProfessionalCategory;

public class Skill {

    public Skill() {
        setSkillId(0);
        setSkill(GenerateRandom.generateRandomBoundedAlphabeticString(15));
        setCategory(new Category(ProfessionalCategory.getProfessionalCategoryById(ProfessionalCategory.selectRandomId())));
    }

    private int skillId;
    private String skill;
    private Category category;

    public int getSkillId() {
        return skillId;
    }

    public String getSkill() {
        return skill;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill c = (Skill) o;

        return skillId == c.skillId
                && skill.equals(c.skill)
                && category.equals(c.category);
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
