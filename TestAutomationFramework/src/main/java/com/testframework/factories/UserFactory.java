package com.testframework.factories;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.enums.ProfessionalCategory;
import com.testframework.models.User;

public class UserFactory {

    public static User createUser(int usernameLength, int emailLength, int passwordLength, int categoryId) {
        return new User(generateUsername(usernameLength),
                generateValidEmail(emailLength),
                generatePassword(passwordLength),
                selectCategory(categoryId));
    }

    public static User createUser(int usernameLength, int emailLength, int passwordLength) {
        return new User (generateUsername(usernameLength),
                generateValidEmail(emailLength),
                generatePassword(passwordLength),
                selectCategory(ProfessionalCategory.selectRandomId()));
    }

    public static String generateUsername(int length) {
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    public static String generateValidEmail(int length) {
        return GenerateRandom.generateRandomBoundedAlphanumericString(length) + "@test.bg";
    }

    public static String generatePassword(int length) {
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    public static ProfessionalCategory selectCategory(int id) {
        return ProfessionalCategory.getProfessionalCategoryById(id);
    }
}
