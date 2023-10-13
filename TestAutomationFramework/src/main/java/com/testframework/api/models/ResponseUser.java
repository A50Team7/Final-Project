package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseUser {

    private int id;
    private String username;
    private List<String> authorities;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private City city;
    private String birthYear;
    private String personalReview;
    private String expertise;
    private List<Skill> skills;

}
