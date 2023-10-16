package com.testframework.api.models;

import lombok.Getter;

@Getter
public class PostResponse {

    private int postId;
    private String content;
    private String picture;
    private String date;
    private String[] likes;
    private String[] comments;
    private int rank;
    private boolean liked;
    private Category category;

}
