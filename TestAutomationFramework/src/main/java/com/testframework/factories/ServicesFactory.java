package com.testframework.factories;

import com.testframework.Utils;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.Services;

import java.text.DecimalFormat;

public class ServicesFactory {


    public static Services createServices() {
        Services services = new Services();
        services.setServiceOne(generateService());
        services.setServiceTwo(generateService());
        services.setServiceThree(generateService());
        services.setServiceFour(generateService());
        services.setServiceFive(generateService());
        services.setWeeklyAvailability((int) generateWeeklyAvailability());

        return services;
    }

    public static String generateService() {
        int length = GenerateRandom.generateLength("services.lowerbound", "services.upperbound");
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    public static String generateService(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    public static double generateWeeklyAvailability() {
        int min = Integer.parseInt(Utils.getConfigPropertyByKey("availability.lowerbound"));
        int max = Integer.parseInt(Utils.getConfigPropertyByKey("availability.upperbound"));
        double value = GenerateRandom.generateRandomBoundedInteger(min, max);
        return Double.parseDouble(new DecimalFormat("#.#").format(value));
    }

    public static double generateWeeklyAvailability(int min, int max) {
        double value = GenerateRandom.generateRandomBoundedDouble(min, max);
        return Double.parseDouble(new DecimalFormat("#.#").format(value));
    }

    public static double generateInvalidWeeklyAvailability() {
        int upperAvailability = Integer.parseInt(Utils.getConfigPropertyByKey("availability.upperbound"));
        return GenerateRandom.generateRandomBoundedInteger(upperAvailability, 500);
    }
}
