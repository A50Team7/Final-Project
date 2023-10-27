package com.testframework.models.interfaces;

import com.testframework.models.Comment;

public interface Commentable {
    public void addComment(Comment comment);

    public void removeComment(Comment comment);
}
