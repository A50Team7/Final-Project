package com.testframework.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Services model representing the services in the application.
 * Instances can be created using the ServicesFactory class.
 *
 * @see com.testframework.factories.ServicesFactory
 */
@Getter
@Setter
@NoArgsConstructor
public class Services {

    public Services(String[] services) {
        if (services.length > 5) throw new IllegalArgumentException("Too many services, conversion impossible.");
        try {
            setServiceOne(services[0]);
            setServiceTwo(services[1]);
            setServiceThree(services[2]);
            setServiceFour(services[3]);
            setServiceFive(services[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore
        }
    }

    private String serviceOne;
    private String serviceTwo;
    private String serviceThree;
    private String serviceFour;
    private String serviceFive;
    private double weeklyAvailability;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services s = (Services) o;
        return Objects.deepEquals(serviceOne, s.serviceOne) &&
                Objects.deepEquals(serviceTwo, s.serviceTwo) &&
                Objects.deepEquals(serviceThree, s.serviceThree) &&
                Objects.deepEquals(serviceFour, s.serviceFour) &&
                Objects.deepEquals(serviceFive, s.serviceFive);
    }

}
