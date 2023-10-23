package pages;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.models.Post;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

public class LatestPostsPage extends BasePage {
    public LatestPostsPage(WebDriver driver, String url) {
        super(driver, url);
    }

    private static String baseLocator = Utils.getUIMappingByKey("viewPosts.container");
    private static String author = Utils.getUIMappingByKey("viewPosts.author");
    private static String content = Utils.getUIMappingByKey("viewPosts.content");
    private static String explorePost = Utils.getUIMappingByKey("viewPosts.explorePost");
    private static String seeProfile = Utils.getUIMappingByKey("viewPosts.seeProfile");
    private static String likeCount = Utils.getUIMappingByKey("viewPosts.likeCount");
    private static String likeButton = Utils.getUIMappingByKey("viewPosts.like");
    private static String dislikeButton = Utils.getUIMappingByKey("viewPosts.dislike");

    public void assertPostMatches(Post post) {
        Assertions.assertAll(
                () -> assertAuthor(post.getAuthor().getUsername(), post.getPostId()),
                () -> assertContent(post.getContent(), post.getPostId()),
                () -> assertLikeCount(post)
        );
    }

    public void assertAuthor(String username, int postId) {
        Assertions.assertEquals(username, actions.getText(authorBy(postId)));
    }

    public void assertContent(String content, int postId) {
        Assertions.assertEquals(content, actions.getText(contentBy(postId)));
    }

    public void assertPostContainerPresent(int postId) {
        actions.waitForElementPresent(By.xpath(getBaseLocator(postId)));
        actions.assertElementPresent(By.xpath(getBaseLocator(postId)));
    }

    public void assertLiked(Post post) {
        int postId = post.getPostId();
        Assertions.assertAll(
                () -> actions.assertElementPresent(dislikeBy(postId)),
                () -> assertLikeCount(post)
        );
    }

    public void assertDisliked(Post post) {
        int postId = post.getPostId();
        Assertions.assertAll(
                () -> actions.assertElementPresent(likeBy(postId)),
                () -> assertLikeCount(post)
        );
    }

    public void assertLikeCount(Post post) {
        int postId = post.getPostId();
        Assertions.assertEquals(post.getLikes(),
                FormatHelper.extractNumber(actions.getText(likeCountBy(postId))),
                "The like count doesn't match.");
    }

    public void explorePost(int postId) {
        actions.clickElement(explorePostBy(postId));
    }

    public void seeProfile(int postId) {
        actions.clickElement(seeProfileBy(postId));
    }

    public void like(Post post) {
        actions.clickElement(likeBy(post.getPostId()));
        post.like();
    }

    public void dislike(Post post) {
        actions.clickElement(dislikeBy(post.getPostId()));
        post.dislike();
    }

    private String getBaseLocator(int postId) {
        return String.format(baseLocator, postId);
    }

    private By authorBy(int postId) {
        return By.xpath(getBaseLocator(postId) + author);
    }

    private By contentBy(int postId) {
        return By.xpath(getBaseLocator(postId) + content);
    }

    private By explorePostBy(int postId) {
        return By.xpath(getBaseLocator(postId) + explorePost);
    }

    private By seeProfileBy(int postId) {
        return By.xpath(getBaseLocator(postId) + seeProfile);
    }

    private By likeCountBy(int postId) {
        return By.xpath(getBaseLocator(postId) + likeCount);
    }

    private By likeBy(int postId) {
        return By.xpath(getBaseLocator(postId) + likeButton);
    }

    private By dislikeBy(int postId) {
        return By.xpath(getBaseLocator(postId) + dislikeButton);
    }
}
