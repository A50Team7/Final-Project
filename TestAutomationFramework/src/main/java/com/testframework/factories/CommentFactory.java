package com.testframework.factories;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.Comment;
import com.testframework.models.Post;
import com.testframework.models.User;

/**
 * A factory class for creating instances of the Comment class.
 *
 * @see Comment
 */
public class CommentFactory {

    /**
     * Creates a comment with the provided post, user, and generated content.
     *
     * @param post the post the comment is associated with
     * @param user the user who created the comment
     * @return the created Comment object
     */
    public static Comment createComment(Post post, User user) {
        return new Comment(
                post,
                user,
                generateContent()
        );
    }

    /**
     * Generates random content for a comment based on the configured length bounds.
     *
     * @return the generated comment content
     */
    public static String generateContent() {
        int length = GenerateRandom.generateLength("comment.content.lowerbound", "comment.content.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    /**
     * Generates random content for a comment with the specified length.
     *
     * @param length the length of the comment content
     * @return the generated comment content
     */
    public static String generateContent(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }
}