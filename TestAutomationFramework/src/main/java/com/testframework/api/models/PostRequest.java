package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.Post;
import com.testframework.models.enums.Visibility;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequest {
    private String content;
    private String picture;
    private boolean isPostPrivate;

    public PostRequest() {
        setContent(GenerateRandom.generateRandomBoundedAlphabeticString(20));
        setPicture("");
        setPostPrivate(true);
    }

    public PostRequest(Post post) {
        setContent(post.getContent());
        setPicture("");
        setPostPrivate(post.getVisibility().equals(Visibility.PRIVATE));
    }

}
