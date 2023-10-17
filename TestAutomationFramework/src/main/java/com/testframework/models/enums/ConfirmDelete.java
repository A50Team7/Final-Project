package com.testframework.models.enums;

import lombok.Getter;

@Getter
public enum ConfirmDelete {
    DELETE("Delete"),
    CANCEL("Cancel");

    private final String stringValue;

    ConfirmDelete(String stringValue) {
        this.stringValue = stringValue;
    }
}
