package com.testframework.api.models;

import java.util.List;

public class ResponseUser {
    //{
    //    "id": 43,
    //    "username": "Nyom",
    //    "authorities": [
    //        "com.community.weare.Models.Role@2ecd0de8"
    //    ],
    //    "email": "nyoom@test.bg",
    //    "firstName": "aaaaa",
    //    "lastNAme": "aaaaa",
    //    "gender": "MALE",
    //    "city": {
    //        "id": 1,
    //        "city": "Sofia",
    //        "country": {}
    //    },
    //    "birthYear": "2010-11-11",
    //    "personalReview": "aaaaaaaaaaa",
    //    "expertise": "All",
    //    "skills": []
    //}

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

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public City getCity() {
        return city;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getPersonalReview() {
        return personalReview;
    }

    public String getExpertise() {
        return expertise;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public void setPersonalReview(String personalReview) {
        this.personalReview = personalReview;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
