package com.testframework.models;

import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Location;
import com.testframework.models.interfaces.Friendable;
import com.testframework.models.interfaces.Postability;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Profile model representing the profiles in the application.
 * Instances can be created using the ProfileFactory class.
 *
 * @see com.testframework.factories.ProfileFactory
 */
@Getter @Setter
public class Profile implements Friendable, Postability {

    /**
     * Constructs an empty Profile with initialized friend list and posts.
     */
    public Profile() {
        friendList = new ArrayList<User>();
        posts = new ArrayList<Post>();
        services = new Services();
    }

    /**
     * Constructs a Profile with the provided first name, last name, and birthday.
     *
     * @param firstName the first name of the profile owner
     * @param lastName the last name of the profile owner
     * @param birthday the birthday of the profile owner
     */
    public Profile(String firstName, String lastName, LocalDate birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        friendList = new ArrayList<User>();
        posts = new ArrayList<Post>();
    }

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Gender gender;
    private Location location;
    private ArrayList<User> friendList;
    private ArrayList<Post> posts;
    private String bio;
    private Services services;

    public void addFriend(User user) {
        if (friendList.contains(user)) throw new IllegalArgumentException("This user is already in the friend list.");
        friendList.add(user);
    }

    public void removeFriend(User user) {
        if (!friendList.contains(user)) throw new IllegalArgumentException("This user is not in the friend list.");
        friendList.remove(user);
    }

    public void addPost(Post post) {
        if (posts.contains(post)) throw new IllegalArgumentException("This post is already in the list.");
        posts.add(post);
    }

    public void removePost(Post post) {
        if (posts.contains(post)) throw new IllegalArgumentException("This post is not in the list.");
        posts.remove(post);
    }

    public List<User> getFriendList() {
        return new ArrayList<User>(friendList);
    }

    public List<Post> getPosts() {
        return new ArrayList<Post>(posts);
    }

    /**
     * Retrieves the full name of the profile owner by combining the first name and last name.
     *
     * @return the full name of the profile
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
