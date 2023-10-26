package com.testframework.factories;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Visibility;

/**
 * A factory class for creating instances of the Post class.
 *
 * @see Post
 */
public class PostFactory {

    /**
     * Creates a post with the provided author and visibility, and generated content.
     *
     * @param author the author of the post
     * @param visibility the visibility setting of the post
     * @return the created Post object
     */
    public static Post createPost(User author, Visibility visibility) {
        return new Post(
                author,
                generateContent(),
                visibility
        );
    }

    /**
     * Generates random content for a post based on the configured length bounds.
     *
     * @return the generated post content
     */
    public static String generateContent() {
        int length = GenerateRandom.generateLength("post.content.lowerbound", "post.content.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    /**
     * Generates random content for a post with the specified length.
     *
     * @param length the length of the post content
     * @return the generated post content
     */
    public static String generateContent(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }
}
