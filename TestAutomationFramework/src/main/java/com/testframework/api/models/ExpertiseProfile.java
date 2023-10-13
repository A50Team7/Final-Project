package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ExpertiseProfile {

    private int id;
    private List<String> skills;
    private Category category;
    private double availability;

}
