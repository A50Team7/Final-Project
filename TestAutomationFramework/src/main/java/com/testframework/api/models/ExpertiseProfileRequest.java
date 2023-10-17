package com.testframework.api.models;

import com.testframework.models.Profile;
import com.testframework.models.Services;
import com.testframework.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ExpertiseProfileRequest {

    public ExpertiseProfileRequest(User user) {
        Services services = user.getProfile().getServices();
        availability = services.getWeeklyAvailability();
        category = new Category(user.getCategory());
        skill1 = services.getServiceOne();
        skill2 = services.getServiceTwo();
        skill3 = services.getServiceThree();
        skill4 = services.getServiceFour();
        skill5 = services.getServiceFive();
    }

    private double availability;
    private Category category;
    private String skill1;
    private String skill2;
    private String skill3;
    private String skill4;
    private String skill5;

}
