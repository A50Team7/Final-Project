package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.Post;
import com.testframework.models.enums.Visibility;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestPost {
    private int postId;
    private String content;
    private String picture;
    private boolean isPostPrivate;

    public RequestPost() {
        setPostId(0);
        setContent(GenerateRandom.generateRandomBoundedAlphabeticString(20));
        setPicture("");
        setPostPrivate(true);
    }

    public RequestPost(Post post) {
        setPostId(0);
        setContent(post.getContent());
        setPicture("");
        setPostPrivate(post.getVisibility().equals(Visibility.PRIVATE));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestPost p = (RequestPost) o;

        return  postId == p.postId
                && content.equals(p.content)
                && picture.equals(p.picture);
    }

}
