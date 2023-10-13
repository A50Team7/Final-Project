package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Post {
    private int postId;
    private String content;
    private String picture;
    private boolean isPostPrivate;

    public Post() {
        setPostId(0);
        setContent(GenerateRandom.generateRandomBoundedAlphabeticString(20));
        setPicture("");
        setPostPrivate(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post p = (Post) o;

        return  postId == p.postId
                && content.equals(p.content)
                && picture.equals(p.picture);
    }
}
