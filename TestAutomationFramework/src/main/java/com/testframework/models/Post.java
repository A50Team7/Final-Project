package com.testframework.models;

import com.testframework.databasehelper.PostHelper;
import com.testframework.models.enums.Visibility;
import com.testframework.models.interfaces.Commentable;
import com.testframework.models.interfaces.Likable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter @Setter
public class Post implements Likable, Commentable {

    public Post(User author, String content, Visibility visibility) {
        setAuthor(author);
        setContent(content);
        setVisibility(visibility);
        setCreationDateTime(LocalDateTime.now());
        comments = new ArrayList<>();
        likes = 0;
    }

    private int postId;
    private User author;
    private LocalDateTime creationDateTime;
    private String content;
    private Visibility visibility;
    private int likes;
    private ArrayList<Comment> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post p = (Post) o;

        return author.equals(p.author)
                && creationDateTime.equals(p.creationDateTime)
                && content.equals(p.content)
                && visibility.equals(p.visibility)
                && likes==p.likes
                && comments.equals(p.comments);
    }

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

    public ArrayList<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public int getPostId() {
        return PostHelper.getPostId(PostHelper.getPost("content", String.format("'%s'", this.content)));
    }
}
