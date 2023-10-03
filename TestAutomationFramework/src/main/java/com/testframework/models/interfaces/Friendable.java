package com.testframework.models.interfaces;

import com.testframework.models.User;

public interface Friendable {
    public void addFriend(User user);

    public void removeFriend(User user);
}
