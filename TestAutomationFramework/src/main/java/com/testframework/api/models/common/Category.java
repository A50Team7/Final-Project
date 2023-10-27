package com.testframework.api.models.common;

import com.testframework.models.enums.ProfessionalCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Category {

    public Category(ProfessionalCategory category) {
        setId(category.getId());
        setName(category.getStringValue());
    }

    private int id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category c = (Category) o;
        return Objects.deepEquals(this.id, c.id) &&
                Objects.deepEquals(this.name, c.name);
    }

}
