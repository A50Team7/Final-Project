package com.testframework.api.models;

import com.testframework.api.models.common.Category;
import com.testframework.models.enums.ProfessionalCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Skill {

    public Skill(String skill, ProfessionalCategory category) {
        setSkillId(0);
        setSkill(skill);
        setCategory(new Category(category));
    }

    private int skillId;
    private String skill;
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill s = (Skill) o;
        return Objects.deepEquals(this.skillId, s.skillId) &&
                Objects.deepEquals(this.skill, s.skill) &&
                Objects.deepEquals(this.category, s.category);
    }

}
