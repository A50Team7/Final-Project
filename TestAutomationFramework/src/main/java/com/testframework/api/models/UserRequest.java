package com.testframework.api.models;

import com.testframework.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest u = (UserRequest) o;

        return authorities.equals(u.authorities)
                && category.equals(u.category)
                && email.equals(u.email)
                && username.equals(u.username);
    }

    private void addAuthority(String string) {
        authorities.add(string);
    }

}
