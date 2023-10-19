package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FriendRequest {

    private int receiverId;
    private String username;

    public FriendRequest(int receiverId, String username) {
        setReceiverId(receiverId);
        setUsername(username);
    }
}
