package com.testframework.generations;

import com.testframework.Utils;

import java.sql.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandom {
    public static String generateRandomBoundedAlphabeticString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

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

    public static int generateRandomBoundedInteger(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public static int generateLength(String lowerKey, String upperKey) {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey(lowerKey));
        int upperbound = Integer.parseInt(Utils.getConfigPropertyByKey(upperKey));
        return GenerateRandom.generateRandomBoundedInteger(lowerbound, upperbound);
    }

    public static double generateRandomBoundedDouble(double lowerBound, double upperBound) {
        Random random = new Random();
        return random.nextDouble() * (upperBound - lowerBound) + lowerBound;
    }

    public static Date generateRandomDate(String lowerKey, String upperKey) {
        Date lowerbound = Date.valueOf(Utils.getConfigPropertyByKey(lowerKey));
        Date upperbound = Date.valueOf(Utils.getConfigPropertyByKey(upperKey));

        return generateRandomDate(lowerbound, upperbound);
    }

    public static Date generateRandomDate(Date firstDate, Date lastDate) {
        long lowerBoundMillis = firstDate.getTime();
        long upperBoundMillis = lastDate.getTime();

        long randomMillis = ThreadLocalRandom.current().nextLong(lowerBoundMillis, upperBoundMillis + 1);

        return new Date(randomMillis);
    }
}
