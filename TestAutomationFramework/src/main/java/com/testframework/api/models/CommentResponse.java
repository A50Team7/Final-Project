package com.testframework.api.models;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CommentResponse {

    private int commentId;
    private String content;
    private UserResponse[] likes;
    private String date;
    private boolean liked;

}
