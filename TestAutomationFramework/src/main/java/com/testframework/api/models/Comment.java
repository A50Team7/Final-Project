package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;

public class Comment {
    private int userId;
    private int postId;
    private String content;

    public Comment(int userId, int postId) {
        setUserId(userId);
        setPostId(postId);
        setContent(GenerateRandom.generateRandomBoundedAlphabeticString(15));
    }

    private int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    private int getPostId() {
        return postId;
    }

    private void setPostId(int postId) {
        this.postId = postId;
    }

    private String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }
}
