package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;

public class PostEditor {
    private String content;
    private String picture;

    private boolean isPrivate;

    public PostEditor() {
        setContent(GenerateRandom.generateRandomBoundedAlphanumericString(20));
        setPicture("");
        setPrivate(false);
    }

    private String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private String getPicture() {
        return picture;
    }

    private void setPicture(String picture) {
        this.picture = picture;
    }


    private void setPrivate(boolean aPublic) {
        isPrivate = aPublic;
    }
}
