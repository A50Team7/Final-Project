package com.testframework.api.models;

import com.testframework.api.models.common.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExpertiseProfileResponse {

    private int id;
    private List<Skill> skills;
    private Category category;
    private double availability;

}
