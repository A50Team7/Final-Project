package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionRequest {

    public ConnectionRequest(int receiverId, String receiverUsername) {
        setId(receiverId);
        setUsername(receiverUsername);
    }

    private int id;
    private String username;

}
