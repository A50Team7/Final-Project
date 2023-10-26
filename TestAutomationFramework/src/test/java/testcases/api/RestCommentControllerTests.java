package testcases.api;

import com.testframework.api.controllers.RestCommentController;
import com.testframework.api.controllers.RestPostController;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.*;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.CommentFactory;
import com.testframework.factories.PostFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.Comment;
import com.testframework.models.Post;
import com.testframework.models.User;
import com.testframework.models.enums.Authority;
import com.testframework.models.enums.Visibility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RestCommentControllerTests extends BaseApiTest {

    private String authCookie;
    private int postId;
    private User user;
    private UserRequest userRequest;
    private Comment comment;
    private int commentId;
    private CommentResponse commentResponse;

    @BeforeEach
    public void setup() {
        user = UserFactory.createUser();
        Post post = PostFactory.createPost(user, Visibility.PUBLIC);
        comment = CommentFactory.createComment(post, user);

        userRequest = new UserRequest(Authority.ROLE_USER.toString(), user);
        RestUserController.createUser(userRequest);
        authCookie = getCookieValue(user);

        PostRequest postRequest = new PostRequest(post);
        PostResponse postResponse = RestPostController.createPost(postRequest, authCookie);
        postId = postResponse.getPostId();

        CommentRequest commentRequest = new CommentRequest(comment);
        commentResponse = RestCommentController.createComment(commentRequest, authCookie);
        commentId = commentResponse.getCommentId();
    }

    @Test
    public void getAllComments() {
        CommentResponse[] comments = RestCommentController.getAllComments(authCookie);
        for (CommentResponse com : comments) {
            int comId = com.getCommentId();
            CommentResponse getCommentResponse = RestCommentController.getSingleComment(comId);

            ApiHelper.assertComment(com, getCommentResponse);
        }
    }

    @Test
    public void getAllCommentsByPost() {
        CommentResponse[] commentsUnderPost = RestCommentController.getAllCommentsOnPost(postId, authCookie);
        for (CommentResponse commentUnderPost : commentsUnderPost) {
            int commentUnderPostId = commentUnderPost.getCommentId();
            CommentResponse getCommentResponse = RestCommentController.getSingleComment(commentUnderPostId);

            ApiHelper.assertComment(commentResponse, getCommentResponse);
        }
    }

    @Test
    public void createComment() {
        ApiHelper.assertComment(comment, commentResponse);
    }

    @Test
    public void getOneComment() {
        CommentResponse getCommentResponse = RestCommentController.getSingleComment(commentId);

        ApiHelper.assertComment(commentResponse, getCommentResponse);
    }

    @Test
    public void editComment() {
        comment.setContent(CommentFactory.generateContent());
        RestCommentController.editComment(commentId, comment.getContent(), authCookie);

        CommentResponse getCommentResponse = RestCommentController.getSingleComment(commentId);

        ApiHelper.assertComment(comment, getCommentResponse);
    }

    @Test
    public void likeComment() {
        CommentResponse likedComment = RestCommentController.likeComment(commentId, authCookie);

        Assertions.assertTrue(likedComment.isLiked());
        Assertions.assertTrue(Arrays.stream(likedComment.getLikes())
                        .anyMatch(x -> x.getUsername().equals(user.getUsername())),
                "The username who liked this comment isn't present in the list with likes.");
    }

    @Test
    public void deleteComment() {
        RestCommentController.deleteComment(commentId, authCookie);

        CommentResponse[] commentsUnderPost = RestCommentController.getAllCommentsOnPost(postId, authCookie);
        Assertions.assertFalse(Arrays.stream(commentsUnderPost)
                        .anyMatch(x -> x.getCommentId() == commentId),
                "The comment is still present.");
    }

    @AfterEach
    public void cleanup() {
        RestPostController.deletePost(postId, authCookie);
        UserHelper.deleteUser("username", String.format("'%s'", userRequest.getUsername()));
    }

}


