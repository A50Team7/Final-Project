package com.testframework.factories;

import com.testframework.Utils;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.Services;
import com.testframework.models.User;

import java.text.DecimalFormat;

/**
 * A factory class for creating instances of the Services class.
 *
 * @see Services
 */
public class ServicesFactory {

    /**
     * Creates a Services object with generated service names and weekly availability.
     *
     * @return the created Services object
     */
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

    /**
     * Generates a random service name.
     *
     * @return the generated service name
     */
    public static String generateService() {
        int length = GenerateRandom.generateLength("services.lowerbound", "services.upperbound");
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    /**
     * Generates a random service name with the specified length.
     *
     * @param length the length of the service name
     * @return the generated service name
     */
    public static String generateService(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }

    /**
     * Generates a random weekly availability value within the configured bounds.
     *
     * @return the generated weekly availability value
     */
    public static double generateWeeklyAvailability() {
        int min = Integer.parseInt(Utils.getConfigPropertyByKey("availability.lowerbound"));
        int max = Integer.parseInt(Utils.getConfigPropertyByKey("availability.upperbound"));
        double value = GenerateRandom.generateRandomBoundedInteger(min, max);
        return Double.parseDouble(new DecimalFormat("#.#").format(value));
    }

    /**
     * Generates a random weekly availability value within the specified bounds.
     *
     * @param min the lower bound for the weekly availability
     * @param max the upper bound for the weekly availability
     * @return the generated weekly availability value
     */
    public static double generateWeeklyAvailability(int min, int max) {
        double value = GenerateRandom.generateRandomBoundedDouble(min, max);
        return Double.parseDouble(new DecimalFormat("#.#").format(value));
    }

    /**
     * Generates an invalid weekly availability value exceeding the upper bound.
     *
     * @return the generated invalid weekly availability value
     */
    public static double generateInvalidWeeklyAvailability() {
        int upperAvailability = Integer.parseInt(Utils.getConfigPropertyByKey("availability.upperbound"));
        return GenerateRandom.generateRandomBoundedInteger(upperAvailability, 500);
    }
}
