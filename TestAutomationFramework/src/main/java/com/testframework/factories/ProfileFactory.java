package com.testframework.factories;

import com.testframework.Utils;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.Profile;
import com.testframework.models.User;
import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Location;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A factory class for creating instances of the Profile class.
 *
 * @see Profile
 */
public class ProfileFactory {

    /**
     * Creates a profile with generated details including name, birthday, and location.
     *
     * @return the created Profile object
     */
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

    /**
     * Generates an invalid name.
     *
     * @return the generated invalid name
     */
    public static String generateInvalidName() {
        int length = GenerateRandom.generateLength("firstName.lowerbound", "firstName.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length - 3) + "%@#";
    }

    /**
     * Generates a random first name based on the configured length bounds.
     *
     * @return the generated first name
     */
    public static String generateFirstName() {
        int length = GenerateRandom.generateLength("firstName.lowerbound", "firstName.upperbound");
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    /**
     * Generates a random first name with the specified length.
     *
     * @param length the length of the first name
     * @return the generated first name
     */
    public static String generateFirstName(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    /**
     * Generates a random last name based on the configured length bounds.
     *
     * @return the generated last name
     */
    public static String generateLastName() {
        int length = GenerateRandom.generateLength("lastName.lowerbound", "lastName.upperbound");
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    /**
     * Generates a random last name with the specified length.
     *
     * @param length the length of the last name
     * @return the generated last name
     */
    public static String generateLastName(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    /**
     * Generates a random birthday within the configured bounds.
     *
     * @return the generated birthday
     */
    public static LocalDate generateBirthday() {
        return GenerateRandom.generateRandomDate("birthday.lowerbound", "birthday.upperbound");
    }

    public static LocalDate generateBirthday(Date firstDate, Date lastDate) {
        return GenerateRandom.generateRandomDate(firstDate, lastDate);
    }

    /**
     * Generates an invalid birthday date exceeding the upper and lower bounds.
     *
     * @return the generated invalid birthday
     */
    public static LocalDate generateInvalidBirthday() {
        Date upperbound = Date.valueOf(Utils.getConfigPropertyByKey("birthday.lowerbound"));
        Date lowerbound = Date.valueOf("0001-01-01");

        return generateBirthday(lowerbound, upperbound);
    }

    /**
     * Generates an impossible birthday date.
     *
     * @return the generated impossible birthday
     */
    public static LocalDate generateImpossibleBirthday() {
        Date lowerbound = Date.valueOf("3000-01-01");
        Date upperbound = Date.valueOf("9999-01-01");

        return generateBirthday(lowerbound, upperbound);
    }

    /**
     * Generates a random bio based on the configured length bounds.
     *
     * @return the generated bio
     */
    public static String generateBio() {
        int length = GenerateRandom.generateLength("bio.lowerbound", "bio.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    /**
     * Generates a random bio with the specified length.
     *
     * @param length the length of the bio
     * @return the generated bio
     */
    public static String generateBio(int length) {
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    /**
     * Selects a location randomly from the available locations.
     *
     * @return the selected Location
     */
    public static Location selectLocation() {
        int id = Location.selectRandomId();
        return Location.getLocationById(id);
    }

    /**
     * Selects a location by the specified ID.
     *
     * @param id the ID of the location to be selected
     * @return the selected Location
     */
    public static Location selectLocation(int id) {
        return Location.getLocationById(id);
    }

}
