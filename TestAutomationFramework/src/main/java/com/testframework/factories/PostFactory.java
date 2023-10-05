package com.testframework.factories;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Visibility;

public class PostFactory {

    public static Post createPost(User author, Visibility visibility) {
        return new Post(
                author,
                generateContent(),
                visibility
        );
    }

    public static String generateContent() {
        int length = GenerateRandom.generateLength("post.content.lowerbound", "post.content.upperbound");
        return GenerateRandom.generateRandomBoundedAlphanumericString(length);
    }

    public static String generateContent(int length) {
        return GenerateRandom.generateRandomBoundedAlphabeticString(length);
    }
}
