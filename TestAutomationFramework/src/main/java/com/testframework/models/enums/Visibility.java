package com.testframework.models.enums;

import lombok.Getter;

@Getter
public enum Visibility {
    PRIVATE("Private"),
    PUBLIC("Public");

    private final String stringValue;

    Visibility(String stringValue) {
        this.stringValue = stringValue;
    }
}
