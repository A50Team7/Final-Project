package com.testframework.api.models;

import com.testframework.api.models.common.Category;
import com.testframework.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserRequest {

    public UserRequest(String authority, User user) {
        authorities = new ArrayList<String>();
        addAuthority(authority);
        setCategory(new Category(user.getCategory()));
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setUsername(user.getUsername());
    }

    private List<String> authorities;
    private Category category;
    private String email;
    private String password;
    private String username;

    private void addAuthority(String string) {
        authorities.add(string);
    }

}
