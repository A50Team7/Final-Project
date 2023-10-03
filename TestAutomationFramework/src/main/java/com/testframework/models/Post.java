package com.testframework.models;

import com.testframework.models.enums.Visibility;
import com.testframework.models.interfaces.Commentable;
import com.testframework.models.interfaces.Likable;
import java.util.ArrayList;

public class Post implements Likable, Commentable {

    public Post(String content, Visibility visibility) {
        setContent(content);
        setVisibility(visibility);
        comments = new ArrayList<>();
        likes = 0;
    }

    private ArrayList<Comment> comments;
    private String content;
    private Visibility visibility;
    private int likes;

    public void like() {
        likes++;
    }

    public void dislike() {
        likes--;
    }

    public void addComment(Comment comment) {
        if (comments.contains(comment)) throw new IllegalArgumentException("This comment already exists.");
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        if (!comments.contains(comment)) throw new IllegalArgumentException("This comment doesn't exist.");
        comments.remove(comment);
    }

    //############# GETTERS #########
    public ArrayList<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public String getContent() {
        return content;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public int getLikes() {
        return likes;
    }

    //############# SETTERS #########
    public void setContent(String content) {
        this.content = content;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

}
