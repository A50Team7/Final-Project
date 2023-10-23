package com.testframework.models;

import com.testframework.databasehelper.UserHelper;
import com.testframework.models.enums.ProfessionalCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter @Setter
public class User {
    public User(String username, String email, String password, ProfessionalCategory category) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCategory(category);
        setRegistrationDate(Date.from(Instant.now()));
    }

    public User(String username, String email, String password, ProfessionalCategory category, Profile profile) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCategory(category);
        setProfile(profile);
        setRegistrationDate(Date.from(Instant.now()));
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

    public int getUserId() {
        return UserHelper.getUserId(UserHelper.getUser("username", String.format("'%s'", this.username)));
    }
}
