package com.testframework.factories;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.Profile;
import com.testframework.models.enums.ProfessionalCategory;
import com.testframework.models.User;
public class UserFactory {

    public static User createUserWithProfile() {
        return new User(
                generateUsername(),
                generateValidEmail(),
                generatePassword(),
                selectCategory(),
                ProfileFactory.createProfile()
        );
    }

    public static User createUserWithProfile(Profile profile) {
        return new User(
                generateUsername(),
                generateValidEmail(),
                generatePassword(),
                selectCategory(),
                profile
        );
    }

    public static User createUser() {
        return new User (
                generateUsername(),
                generateValidEmail(),
                generatePassword(),
                selectCategory()
        );
    }

    //############# GENERATORS #########
    public static String generateUsername() {
        int length = GenerateRandom.generateLength("username.lowerbound", "username.upperbound");
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    public static String generateUsername(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    public static String generateValidEmail() {
        int length = GenerateRandom.generateLength("email.lowerbound", "email.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length) + "@test.bg";
    }

    public static String generateValidEmail(int length) {
        return GenerateRandom.generateRandomBoundedAlphanumericString(length) + "@test.bg";
    }

    public static String generatePassword() {
        int length = GenerateRandom.generateLength("password.lowerbound", "password.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length) + "!";
    }

    public static String generatePassword(int length) {
        return GenerateRandom.generateRandomBoundedAlphanumericString(length - 1) + "!";
    }

    public static ProfessionalCategory selectCategory() {
        int id = ProfessionalCategory.selectRandomId();
        return ProfessionalCategory.getProfessionalCategoryById(id);
    }

    public static ProfessionalCategory selectCategory(int id) {
        return ProfessionalCategory.getProfessionalCategoryById(id);
    }
}
