package com.testframework.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Services model representing the services in the application.
 * Instances can be created using the ServicesFactory class.
 *
 * @see com.testframework.factories.ServicesFactory
 */
@Getter @Setter
public class Services {

    private String serviceOne;
    private String serviceTwo;
    private String serviceThree;
    private String serviceFour;
    private String serviceFive;
    private double weeklyAvailability;

}
