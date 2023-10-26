package com.testframework.models.enums;

import lombok.Getter;

/**
 * Gender model representing the possible gender options in the application.
 */
@Getter
public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE");

    private String stringValue;
    Gender(String stringValue) {
        this.stringValue = stringValue;
    }

}
