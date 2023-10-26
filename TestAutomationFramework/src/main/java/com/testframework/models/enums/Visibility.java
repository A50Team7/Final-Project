package com.testframework.models.enums;

import lombok.Getter;

/**
 * Visibility model representing the possible visibility options in the application.
 */
@Getter
public enum Visibility {

    PRIVATE("Private"),
    PUBLIC("Public");

    private final String stringValue;

    Visibility(String stringValue) {
        this.stringValue = stringValue;
    }
    
}
