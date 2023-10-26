package com.testframework.models;

import com.testframework.Utils;
import com.testframework.databasehelper.CommentHelper;
import com.testframework.models.interfaces.Likable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Comment model representing the comments in the application.
 * Instances can be created using the CommentFactory class.
 *
 * @see com.testframework.factories.CommentFactory
 */
@Getter @Setter
public class Comment implements Likable {

    /**
     * Constructs a Comment with the provided post, author, and content.
     * The creation date and time are set to the current date based on the configured time zone.
     * The comment is added to the corresponding post.
     *
     * @param post the post the comment is associated with
     * @param author the author of the comment
     * @param content the content of the comment
     */
    public Comment(Post post, User author, String content) {
        setPost(post);
        setAuthor(author);
        setCreationDateTime(LocalDateTime.now(ZoneId.of(Utils.getConfigPropertyByKey("weare.timeZone"))));
        setContent(content);
        likes = 0;
        post.addComment(this);
    }

    private Post post;
    private User author;
    private LocalDateTime creationDateTime;
    private String content;
    private int likes;

    public void like() {
        likes++;
    }

    public void dislike() {
        likes--;
    }

    /**
     * Retrieves the comment ID by searching the database for the comment's content.
     * Connects to the database using CommentHelper.
     *
     * @return the comment ID if found, or -1 if not found
     * @see CommentHelper
     */
    public int getCommentId() {
        return CommentHelper.getCommentId(CommentHelper.getComment("content", String.format("'%s'", this.content)));
    }
}
