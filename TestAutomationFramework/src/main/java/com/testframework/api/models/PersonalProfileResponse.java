package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonalProfileResponse extends PersonalProfileRequest {

    private String personalReview;
    private String memberSince;

}
