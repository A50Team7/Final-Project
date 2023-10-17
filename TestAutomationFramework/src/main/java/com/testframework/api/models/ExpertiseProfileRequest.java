package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ExpertiseProfileRequest {

    private double availability;
    private Category category;
    private String skill1;
    private String skill2;
    private String skill3;
    private String skill4;
    private String skill5;

}
