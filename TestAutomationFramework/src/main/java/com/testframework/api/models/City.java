package com.testframework.api.models;

import java.util.List;

public class City {
    private int id;
    private String city;
    private Country country;

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
