package com.testframework.models;

import com.testframework.models.enums.ProfessionalCategory;

public class User {
    public User(String username, String email, String password, ProfessionalCategory category) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCategory(category);
        profile = new Profile();
    }

    private String username;
    private String email;
    private String password;
    private ProfessionalCategory category;
    private Profile profile;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ProfessionalCategory getCategory() {
        return category;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setCategory(ProfessionalCategory category) {
        this.category = category;
    }
}
