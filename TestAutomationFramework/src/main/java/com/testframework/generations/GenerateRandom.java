package com.testframework.generations;

import com.testframework.Utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A utility class for generating random data.
 * Don't use directly in tests.
 *
 * @see com.testframework.factories
 */
public class GenerateRandom {

    /**
     * Generates a random string of alphabetic characters within the specified length.
     *
     * @param length the length of the string to generate
     * @return the generated random string
     */
    public static String generateRandomBoundedAlphabeticString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * Generates a random string of alphanumeric characters within the specified length.
     *
     * @param length the length of the string to generate
     * @return the generated random string
     */
    public static String generateRandomBoundedAlphanumericString(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * Generates a random integer within the specified lower and upper bounds.
     *
     * @param lowerBound the lower bound of the random integer
     * @param upperBound the upper bound of the random integer
     * @return the generated random integer
     */
    public static int generateRandomBoundedInteger(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }

    /**
     * Generates a random integer within the specified lower and upper bounds.
     *
     * @param lowerKey the key for the lower bound configuration
     * @param upperKey the key for the upper bound configuration
     * @return the generated random length
     */
    public static int generateLength(String lowerKey, String upperKey) {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey(lowerKey));
        int upperbound = Integer.parseInt(Utils.getConfigPropertyByKey(upperKey));
        return GenerateRandom.generateRandomBoundedInteger(lowerbound, upperbound);
    }

    /**
     * Generates a random double within the specified lower and upper bounds.
     *
     * @param lowerBound the lower bound of the random double
     * @param upperBound the upper bound of the random double
     * @return the generated random double
     */
    public static double generateRandomBoundedDouble(double lowerBound, double upperBound) {
        Random random = new Random();
        return random.nextDouble() * (upperBound - lowerBound) + lowerBound;
    }

    /**
     * Generates a random date within the specified lower and upper bounds.
     *
     * @param lowerKey the key for the lower bound configuration
     * @param upperKey the key for the upper bound configuration
     * @return the generated random date
     */
    public static LocalDate generateRandomDate(String lowerKey, String upperKey) {
        Date lowerbound = Date.valueOf(Utils.getConfigPropertyByKey(lowerKey));
        Date upperbound = Date.valueOf(Utils.getConfigPropertyByKey(upperKey));

        return generateRandomDate(lowerbound, upperbound);
    }

    /**
     * Generates a random date within the specified lower and upper bounds.
     *
     * @param firstDate the lower bound date
     * @param secondDate the upper bound date
     * @return the generated random date
     */
    public static LocalDate generateRandomDate(Date firstDate, Date secondDate) {
        long lowerBoundMillis = firstDate.getTime();
        long upperBoundMillis = secondDate.getTime();

        long randomMillis = ThreadLocalRandom.current().nextLong(lowerBoundMillis, upperBoundMillis + 1);

        return new Date(randomMillis).toLocalDate();
    }
}
