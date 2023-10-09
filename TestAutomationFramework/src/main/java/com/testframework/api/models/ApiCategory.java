package com.testframework.api.models;

import com.testframework.models.enums.ProfessionalCategory;

public class ApiCategory {

    public ApiCategory(ProfessionalCategory category) {
        setId(category.getId());
        setName(category.getStringValue());
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiCategory c = (ApiCategory) o;

        return id == c.id
                && name.equals(c.name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
