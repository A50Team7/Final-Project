package com.testframework.api.models;

import com.google.gson.annotations.SerializedName;
import com.testframework.api.models.common.City;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    private int id;
    private String username;
    private List<String> authorities;
    private String email;
    private String firstName;
    // this field has a typo in the backend, return and fix it here, when the bug is also fixed
    @SerializedName("lastNAme") private String lastName;
    private String gender;
    private City city;
    private String birthYear;
    private String personalReview;
    private String expertise;
    private String[] skills;

}
