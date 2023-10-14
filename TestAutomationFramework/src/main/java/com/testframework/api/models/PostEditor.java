package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostEditor {
    private String content;
    private String picture;

    private boolean isPrivate;

    public PostEditor(String newContent) {
        setContent(newContent);
        setPicture("");
        setPrivate(true);
    }

}
