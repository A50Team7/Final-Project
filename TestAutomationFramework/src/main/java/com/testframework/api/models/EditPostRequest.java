package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EditPostRequest {

    public EditPostRequest(String newContent) {
        setContent(newContent);
        setPicture("");
        setPrivate(true);
    }

    private String content;
    private String picture;
    private boolean isPrivate;

}
