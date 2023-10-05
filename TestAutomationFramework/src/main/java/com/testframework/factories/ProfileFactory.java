package com.testframework.factories;

import com.testframework.Utils;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.Profile;
import com.testframework.models.enums.Location;
import java.sql.Date;
import java.text.DecimalFormat;

public class ProfileFactory {

    public static Profile createProfile() {
        Profile profile = new Profile(
                generateFirstName(),
                generateLastName(),
                generateBirthday()
        );
        profile.setLocation(selectLocation());
        profile.setBio(generateBio());
        profile.setWeeklyAvailability(generateWeeklyAvailability());
        return profile;
    }

    //############# GENERATORS #########

    public static String generateFirstName() {
        int length = GenerateRandom.generateLength("firstName.lowerbound", "firstName.upperbound");
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    public static String generateFirstName(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    public static String generateLastName() {
        int length = GenerateRandom.generateLength("lastName.lowerbound", "lastName.upperbound");
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    public static String generateLastName(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    public static Date generateBirthday() {
        return GenerateRandom.generateRandomDate("birthday.lowerbound", "birthday.upperbound");
    }

    public static Date generateBirthday(Date firstDate, Date lastDate) {
        return GenerateRandom.generateRandomDate(firstDate, lastDate);
    }

    public static String generateBio() {
        int length = GenerateRandom.generateLength("bio.lowerbound", "bio.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    public static String generateBio(int length) {
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    public static Location selectLocation() {
        int id = Location.selectRandomId();
        return Location.getLocationById(id);
    }

    public static Location selectLocation(int id) {
        return Location.getLocationById(id);
    }

    public static double generateWeeklyAvailability() {
        int min = Integer.parseInt(Utils.getConfigPropertyByKey("availability.lowerbound"));
        int max = Integer.parseInt(Utils.getConfigPropertyByKey("availability.upperbound"));
        double value = GenerateRandom.generateRandomBoundedDouble(min, max);
        return Double.parseDouble(new DecimalFormat("#.#").format(value));
    }

    public static double generateWeeklyAvailability(int min, int max) {
        double value = GenerateRandom.generateRandomBoundedDouble(min, max);
        return Double.parseDouble(new DecimalFormat("#.#").format(value));
    }
}
