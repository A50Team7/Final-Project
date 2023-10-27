package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendRequest {

    public FriendRequest(int receiverId, String username) {
        setReceiverId(receiverId);
        setUsername(username);
    }

    private int receiverId;
    private String username;

}
