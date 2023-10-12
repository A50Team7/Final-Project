package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;

public class PostEditor {
    private String content;
    private String picture;

    private boolean isPublic;

    public PostEditor() {
        setContent(GenerateRandom.generateRandomBoundedAlphanumericString(20));
        setPicture("");
        setPublic(true);
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

    private boolean isPublic() {
        return isPublic;
    }

    private void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
