package com.testframework.api.models;

import com.testframework.models.enums.Location;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class City {

    public City(Location location) {
        id = location.getId();
        city = location.getStringValue();
    }

    private int id;
    private String city;
    private Country country;

}
