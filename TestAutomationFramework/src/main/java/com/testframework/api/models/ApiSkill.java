package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.enums.ProfessionalCategory;

public class ApiSkill {

    public ApiSkill() {
        setSkillId(0);
        setSkill(GenerateRandom.generateRandomBoundedAlphabeticString(15));
        setCategory(new ApiCategory(ProfessionalCategory.getProfessionalCategoryById(ProfessionalCategory.selectRandomId())));
    }

    private int skillId;
    private String skill;
    private ApiCategory category;

    public int getSkillId() {
        return skillId;
    }

    public String getSkill() {
        return skill;
    }

    public ApiCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiSkill c = (ApiSkill) o;

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

    public void setCategory(ApiCategory category) {
        this.category = category;
    }
}
