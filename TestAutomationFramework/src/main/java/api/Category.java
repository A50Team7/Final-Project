package api;

import com.testframework.models.enums.ProfessionalCategory;

public class Category {

    public Category(ProfessionalCategory category) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
