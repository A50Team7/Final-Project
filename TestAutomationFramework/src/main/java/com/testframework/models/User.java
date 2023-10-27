package com.testframework.models;

import com.testframework.Utils;
import com.testframework.api.models.UserResponse;
import com.testframework.databasehelper.UserHelper;
import com.testframework.models.enums.ProfessionalCategory;
import com.testframework.models.interfaces.Dated;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * User model representing users in the application.
 * Instances can be created using the UserFactory class.
 *
 * @see com.testframework.factories.UserFactory
 */
@Getter @Setter
public class User implements Dated {

    /**
     * Constructs a User object with the provided username, email, password, and category.
     * The registration date is set to the current date based on the configured time zone.
     *
     * @param username the username of the user
     * @param email    the email of the user
     * @param password the password of the user
     * @param category the professional category of the user
     */
    public User(String username, String email, String password, ProfessionalCategory category) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCategory(category);
        setProfile(new Profile());
        setRegistrationDate(LocalDate.now(ZoneId.of(Utils.getConfigPropertyByKey("weare.timeZone"))));
    }

    /**
     * Constructs a User object with the provided username, email, and category.
     * Used when converting from UserResponse to User.
     *
     * @param username the username of the user
     * @param email    the email of the user
     * @param category the professional category of the user
     *
     * @see com.testframework.conversions.UserConversion
     */
    public User(String username, String email, ProfessionalCategory category) {
        setUsername(username);
        setEmail(email);
        setCategory(category);
        setProfile(new Profile());
    }

    /**
     * Constructs a User object with the provided username, email, password, category, and profile.
     * The registration date is set to the current date based on the configured time zone.
     *
     * @param username the username of the user
     * @param email    the email of the user
     * @param password the password of the user
     * @param category the professional category of the user
     * @param profile  the profile of the user
     */
    public User(String username, String email, String password, ProfessionalCategory category, Profile profile) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCategory(category);
        setProfile(profile);
        setRegistrationDate(LocalDate.now(ZoneId.of(Utils.getConfigPropertyByKey("weare.timeZone"))));
    }

    /**
     * Constructs a User object with the provided username, email, category, and profile.
     * Used when converting from UserResponse to User.
     *
     * @param username the username of the user
     * @param email    the email of the user
     * @param category the professional category of the user
     * @param profile  the profile of the user
     *
     * @see com.testframework.conversions.UserConversion
     */
    public User(String username, String email, ProfessionalCategory category, Profile profile) {
        setUsername(username);
        setEmail(email);
        setCategory(category);
        setProfile(profile);
    }

    private Profile profile;
    private String username;
    private String email;
    private String password;
    private ProfessionalCategory category;
    private LocalDate registrationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User u = (User) o;
        return profile.equals(u.profile) &&
                Objects.deepEquals(username, u.username) &&
                Objects.deepEquals(email, u.email) &&
                category == u.category;
    }

    /**
     * Retrieves the user ID by searching the database for the user's username.
     * Connects to the database using UserHelper.
     *
     * @return the user ID if found, or -1 if not found
     * @see UserHelper
     */
    public int getUserId() {
        return UserHelper.getUserIdByUsername(this.getUsername());
    }

}
