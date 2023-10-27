package com.testframework.models;

import com.testframework.Utils;
import com.testframework.databasehelper.PostHelper;
import com.testframework.models.enums.Visibility;
import com.testframework.models.interfaces.Commentable;
import com.testframework.models.interfaces.Likable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Post model representing the posts in the application.
 * Instances can be created using the PostFactory class.
 *
 * @see com.testframework.factories.PostFactory
 */
@Getter
@Setter
public class Post implements Likable, Commentable {

    /**
     * Constructs a Post with the provided author, content, and visibility.
     * The creation date and time are set to the current date based on the configured time zone.
     *
     * @param author     the author of the post
     * @param content    the content of the post
     * @param visibility the visibility of the post
     */
    public Post(User author, String content, Visibility visibility) {
        setAuthor(author);
        setContent(content);
        setVisibility(visibility);
        setCreationDateTime(LocalDateTime.now(ZoneId.of(Utils.getConfigPropertyByKey("weare.timeZone"))));
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

    public ArrayList<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    /**
     * Retrieves the post ID by searching the database for the post's content.
     * Connects to the database using PostHelper.
     *
     * @return the post ID if found, or -1 if not found
     * @see PostHelper
     */
    public int getPostId() {
        return PostHelper.getPostId(PostHelper.getPost("content", String.format("'%s'", this.content)));
    }
}
