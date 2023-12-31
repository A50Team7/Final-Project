package com.testframework.models.enums;

import lombok.Getter;

/**
 * Model representing the possible options in the 'delete pages' in the application.
 */
@Getter
public enum ConfirmDelete {

    DELETE("Delete"),
    CANCEL("Cancel");

    private final String stringValue;

    ConfirmDelete(String stringValue) {
        this.stringValue = stringValue;
    }

}
