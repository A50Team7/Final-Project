package com.testframework.models;

import com.testframework.Utils;
import com.testframework.databasehelper.UserHelper;
import com.testframework.models.enums.ProfessionalCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * User model representing users in the application.
 * Instances can be created using the UserFactory class.
 *
 * @see com.testframework.factories.UserFactory
 */
@Getter @Setter
public class User {

    /**
     * Constructs a User object with the provided username, email, password, and category.
     * The registration date is set to the current date based on the configured time zone.
     *
     * @param username the username of the user
     * @param email the email of the user
     * @param password the password of the user
     * @param category the professional category of the user
     */
    public User(String username, String email, String password, ProfessionalCategory category) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCategory(category);
        setRegistrationDate(LocalDate.now(ZoneId.of(Utils.getConfigPropertyByKey("weare.timeZone"))));
    }

    /**
     * Constructs a User object with the provided username, email, password, category, and profile.
     * The registration date is set to the current date based on the configured time zone.
     *
     * @param username the username of the user
     * @param email the email of the user
     * @param password the password of the user
     * @param category the professional category of the user
     * @param profile the profile of the user
     */
    public User(String username, String email, String password, ProfessionalCategory category, Profile profile) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCategory(category);
        setProfile(profile);
        setRegistrationDate(LocalDate.now(ZoneId.of(Utils.getConfigPropertyByKey("weare.timeZone"))));
    }

    private Profile profile;
    private String username;
    private String email;
    private String password;
    private ProfessionalCategory category;
    private LocalDate registrationDate;


    /**
     * Retrieves the user ID by searching the database for the user's username.
     * Connects to the database using UserHelper.
     *
     * @return the user ID if found, or -1 if not found
     * @see UserHelper
     */
    public int getUserId() {
        return UserHelper.getUserId(UserHelper.getUser("username", String.format("'%s'", this.username)));
    }
}
