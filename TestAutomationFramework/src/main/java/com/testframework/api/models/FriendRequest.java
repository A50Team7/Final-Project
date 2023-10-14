package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FriendRequest {

    private int id;
    private String username;

    public FriendRequest(int id, String username) {
        setId(id);
        setUsername(username);
    }
}
