package com.testframework.models;

import com.testframework.models.enums.ProfessionalCategory;

import java.util.Date;

public class User {
    public User(String username, String email, String password, ProfessionalCategory category) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCategory(category);
    }

    public User(String username, String email, String password, ProfessionalCategory category, Profile profile) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCategory(category);
        setProfile(profile);
    }

    private Profile profile;
    private String username;
    private String email;
    private String password;
    private ProfessionalCategory category;
    private Date registrationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User u = (User) o;

        return username.equals(u.username)
                && email.equals(u.email)
                && password.equals(u.password)
                && category.equals(u.category)
                && registrationDate.equals(u.registrationDate)
                && profile.equals(u.profile);
    }

    //############# GETTERS #########
    public Profile getProfile() {
        return profile;
    }

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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    //############# SETTERS #########
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCategory(ProfessionalCategory category) {
        this.category = category;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
