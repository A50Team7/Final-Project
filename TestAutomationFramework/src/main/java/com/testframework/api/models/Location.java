package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Location {

    public Location(com.testframework.models.enums.Location location) {
        city = new City(location);
        id = 1;
    }

    private City city;
    private int id;

}
