package com.testframework.models;

import com.testframework.models.interfaces.Likable;

import java.time.LocalDateTime;

public class Comment implements Likable {

    public Comment(Post post, User author, String content) {
        setPost(post);
        setAuthor(author);
        setCreationDateTime(LocalDateTime.now());
        setContent(content);
        likes = 0;
        post.addComment(this);
    }

    private User author;
    private LocalDateTime creationDateTime;
    private Post post;
    private String content;
    private int likes;

    public void like() {
        likes++;
    }

    public void dislike() {
        likes--;
    }

    //############# GETTERS #########

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public Post getPost() {
        return post;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    //############# SETTERS #########
    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
