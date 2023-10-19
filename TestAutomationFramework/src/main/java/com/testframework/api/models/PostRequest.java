package com.testframework.api.models;

import com.google.gson.annotations.SerializedName;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.Post;
import com.testframework.models.enums.Visibility;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequest {
    private String content;
    private String picture;
    @SerializedName("public") private boolean isPublic;

    public PostRequest() {
        setContent(GenerateRandom.generateRandomBoundedAlphabeticString(20));
        setPicture("");
        setPublic(true);
    }

    public PostRequest(Post post) {
        setContent(post.getContent());
        setPicture("");
        setPublic(post.getVisibility().equals(Visibility.PUBLIC));
    }

}
