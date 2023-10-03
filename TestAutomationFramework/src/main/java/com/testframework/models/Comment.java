package com.testframework.models;

import com.testframework.models.interfaces.Likable;

public class Comment implements Likable {

    public Comment(String content) {
        setContent(content);
        likes = 0;
    }

    private String content;
    private int likes;

    public void like() {
        likes++;
    }

    public void dislike() {
        likes--;
    }

    //############# GETTERS #########
    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    //############# SETTERS #########
    public void setContent(String content) {
        this.content = content;
    }
}
