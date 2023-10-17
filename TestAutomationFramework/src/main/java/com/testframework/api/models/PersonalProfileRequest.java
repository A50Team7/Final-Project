package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonalProfileRequest {

    private String birthYear;
    private String firstName;
    private String lastName;
    private Location location;
    private String picturePrivacy;
    private String sex;

}
