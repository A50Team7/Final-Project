package com.testframework.factories;

import com.testframework.Utils;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.Profile;
import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Location;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.Instant;

public class ProfileFactory {

    public static Profile createProfile() {
        Profile profile = new Profile(
                generateFirstName(),
                generateLastName(),
                generateBirthday()
        );
        profile.setGender(Gender.MALE);
        profile.setLocation(selectLocation());
        profile.setBio(generateBio());
        profile.setServices(ServicesFactory.createServices());
        return profile;
    }

    //############# GENERATORS #########

    public static String generateInvalidName() {
        int length = GenerateRandom.generateLength("firstName.lowerbound", "firstName.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length - 3) + "%@#";
    }

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

    public static Date generateInvalidBirthday() {
        Date upperbound = Date.valueOf(Utils.getConfigPropertyByKey("birthday.lowerbound"));
        Date lowerbound = Date.valueOf("0001-01-01");

        return generateBirthday(lowerbound, upperbound);
    }

    public static Date generateImpossibleBirthday() {
        Date lowerbound = Date.valueOf("3000-01-01");
        Date upperbound = Date.valueOf("9999-01-01");

        return generateBirthday(lowerbound, upperbound);
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


}
