package com.testframework.models.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private String stringValue;
    Gender(String stringValue) {
        this.stringValue = stringValue;
    }
}
