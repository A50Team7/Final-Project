package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class City {

    private int id;
    private String city;
    private Country country;

}
