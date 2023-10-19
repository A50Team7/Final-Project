package com.testframework.api.models;

import com.testframework.generations.GenerateRandom;
import com.testframework.models.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentRequest {

    public CommentRequest(Comment comment) {
        setUserId(comment.getAuthor().getUserId());
        setPostId(comment.getPost().getPostId());
        setContent(comment.getContent());
    }
    public CommentRequest(int userId, int postId){
        setUserId(userId);
        setPostId(postId);
        setContent(GenerateRandom.generateRandomBoundedAlphanumericString(30));
    }



    private int userId;
    private int postId;
    private String content;

}
