package com.testframework.api.models;

import com.testframework.models.User;

import java.util.ArrayList;
import java.util.List;

public class ApiUser {

    public ApiUser(String authority, User user) {
        authorities = new ArrayList<String>();
        addAuthority(authority);
        setCategory(new ApiCategory(user.getCategory()));
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setUsername(user.getUsername());
    }

    private List<String> authorities;
    private ApiCategory category;
    private String email;
    private String password;
    private String username;

    public List<String> getAuthorities() {
        return authorities;
    }

    public ApiCategory getCategory() {
        return category;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiUser u = (ApiUser) o;

        return authorities.equals(u.authorities)
                && category.equals(u.category)
                && email.equals(u.email)
                && password.equals(u.password)
                && username.equals(u.username);
    }

    private void addAuthority(String string) {
        authorities.add(string);
    }

    public void setCategory(ApiCategory category) {
        this.category = category;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}