package com.testframework.factories;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.Comment;
import com.testframework.models.Post;
import com.testframework.models.User;

public class CommentFactory {

    public static Comment createComment(Post post, User user) {
        return new Comment(
                post,
                user,
                generateContent()
        );
    }

    public static String generateContent() {
        int length = GenerateRandom.generateLength("comment.content.lowerbound", "comment.content.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    public static String generateContent(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }
}
