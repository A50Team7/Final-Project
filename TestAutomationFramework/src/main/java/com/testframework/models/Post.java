package com.testframework.models;

import com.testframework.models.enums.Visibility;
import com.testframework.models.interfaces.Commentable;
import com.testframework.models.interfaces.Likable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post implements Likable, Commentable {

    public Post(User author, String content, Visibility visibility) {
        setAuthor(author);
        setContent(content);
        setVisibility(visibility);
        setCreationDateTime(LocalDateTime.now());
        comments = new ArrayList<>();
        likes = 0;
    }

    private User author;
    private LocalDateTime creationDateTime;
    private String content;
    private Visibility visibility;
    private int likes;
    private ArrayList<Comment> comments;

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
    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
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

    public ArrayList<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    //############# SETTERS #########
    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

}
