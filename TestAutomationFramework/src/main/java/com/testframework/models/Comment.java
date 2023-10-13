package com.testframework.models;

import com.testframework.models.interfaces.Likable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Comment implements Likable {

    public Comment(Post post, User author, String content) {
        setAuthor(author);
        setCreationDateTime(LocalDateTime.now());
        setContent(content);
        likes = 0;
        post.addComment(this);
    }

    private User author;
    private LocalDateTime creationDateTime;
    private String content;
    private int likes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment c = (Comment) o;

        return author.equals(c.author)
                && creationDateTime.equals(c.creationDateTime)
                && content.equals(c.content)
                && likes==c.likes;
    }

    public void like() {
        likes++;
    }

    public void dislike() {
        likes--;
    }

}
