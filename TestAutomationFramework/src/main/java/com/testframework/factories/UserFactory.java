package com.testframework.factories;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.Profile;
import com.testframework.models.enums.ProfessionalCategory;
import com.testframework.models.User;

/**
 * A factory class for creating instances of the User class.
 *
 * @see User
 */
public class UserFactory {

    /**
     * Creates a user with a profile.
     *
     * @return the created User object
     */
    public static User createUserWithProfile() {
        return new User(
                generateUsername(),
                generateValidEmail(),
                generatePassword(),
                selectCategory(),
                ProfileFactory.createProfile()
        );
    }

    /**
     * Creates an admin with a profile.
     *
     * @return the created User object with admin privileges
     */
    public static User createAdminWithProfile() {
        return new User(
                generateAdminUsername(),
                generateValidEmail(),
                generatePassword(),
                selectCategory(),
                ProfileFactory.createProfile()
        );
    }

    /**
     * Creates a user with a specified profile.
     *
     * @param profile the profile to be associated with the user
     * @return the created User object with the specified profile
     */
    public static User createUserWithProfile(Profile profile) {
        return new User(
                generateUsername(),
                generateValidEmail(),
                generatePassword(),
                selectCategory(),
                profile
        );
    }

    /**
     * Creates a user.
     *
     * @return the created User object
     */
    public static User createUser() {
        return new User(
                generateUsername(),
                generateValidEmail(),
                generatePassword(),
                selectCategory()
        );
    }

    /**
     * Creates an admin.
     *
     * @return the created User object with admin privileges
     */
    public static User createAdmin() {
        return new User(
                generateAdminUsername(),
                generateValidEmail(),
                generatePassword(),
                selectCategory()
        );
    }

    /**
     * Generates a random username based on the configured length bounds.
     *
     * @return the generated username
     */
    public static String generateUsername() {
        int length = GenerateRandom.generateLength("username.lowerbound", "username.upperbound");
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    /**
     * Generates a random admin username based on the configured length bounds.
     *
     * @return the generated admin username
     */
    public static String generateAdminUsername() {
        int length = GenerateRandom.generateLength("username.lowerbound", "username.upperbound") - 5;
        return String.format("admin%s", GenerateRandom.generateRandomBoundedAlphabeticString(length));
    }

    /**
     * Generates a random username with the specified length.
     *
     * @param length the length of the username
     * @return the generated username
     */
    public static String generateUsername(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    /**
     * Generates a random admin username with the specified length.
     *
     * @param length the length of the admin username
     * @return the generated admin username
     */
    public static String generateAdminUsername(int length) {
        return String.format("admin%s", GenerateRandom.generateRandomBoundedAlphabeticString(length - 5));
    }

    /**
     * Generates a valid email address based on the configured length bounds.
     *
     * @return the generated valid email address
     */
    public static String generateValidEmail() {
        int length = GenerateRandom.generateLength("email.lowerbound", "email.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length) + "@test.bg";
    }

    /**
     * Generates an invalid email address based on the configured length bounds.
     *
     * @return the generated invalid email address
     */
    public static String generateInvalidEmail() {
        int length = GenerateRandom.generateLength("email.lowerbound", "email.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length) + "@test";
    }

    /**
     * Generates a valid email address with the specified length.
     *
     * @param length the length of the email address
     * @return the generated valid email address
     */
    public static String generateValidEmail(int length) {
        return GenerateRandom.generateRandomBoundedAlphanumericString(length) + "@test.bg";
    }

    /**
     * Generates a random password based on the configured length bounds.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        int length = GenerateRandom.generateLength("password.lowerbound", "password.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length) + "!";
    }

    /**
     * Generates a random password with the specified length.
     *
     * @param length the length of the password
     * @return the generated password
     */
    public static String generatePassword(int length) {
        return GenerateRandom.generateRandomBoundedAlphanumericString(length - 1) + "!";
    }

    /**
     * Selects a professional category randomly from the available categories.
     *
     * @return the selected ProfessionalCategory
     */
    public static ProfessionalCategory selectCategory() {
        int id = ProfessionalCategory.selectRandomId();
        return ProfessionalCategory.getProfessionalCategoryById(id);
    }

    /**
     * Selects a professional category by the specified ID.
     *
     * @param id the ID of the professional category to be selected
     * @return the selected ProfessionalCategory
     */
    public static ProfessionalCategory selectCategory(int id) {
        return ProfessionalCategory.getProfessionalCategoryById(id);
    }
}
