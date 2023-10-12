package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;

public class Post {
    private int postId;
    private String content;
    private String picture;
    private boolean isPostPrivate;

    public Post() {
        setPostId(0);
        setPostContent(GenerateRandom.generateRandomBoundedAlphabeticString(20));
        setPicture("");
        setPostPrivate(false);
    }

    protected int getPostId() {
        return postId;
    }

    protected void setPostId(int postId) {
        this.postId = postId;
    }

    protected String getContent() {
        return content;
    }

    protected void setPostContent(String content) {
        this.content = content;
    }

    protected String getPicture() {
        return picture;
    }

    protected void setPicture(String picture) {
        this.picture = picture;
    }

    protected boolean isPostPrivate() {
        return isPostPrivate;
    }

    protected void setPostPrivate(boolean postPrivate) {
        this.isPostPrivate = postPrivate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post p = (Post) o;

        return postId == p.postId
                && content.equals(p.content)
                && picture.equals(p.picture);
    }
}
