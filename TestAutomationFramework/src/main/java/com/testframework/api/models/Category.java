package com.testframework.api.models;

import com.testframework.models.enums.ProfessionalCategory;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Category {

    public Category(ProfessionalCategory category) {
        setId(category.getId());
        setName(category.getStringValue());
    }

    private int id;
    private String name;

}
