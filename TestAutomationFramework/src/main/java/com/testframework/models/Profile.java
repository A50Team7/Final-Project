package com.testframework.models;

import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Location;
import com.testframework.models.interfaces.Dated;
import com.testframework.models.interfaces.Friendable;
import com.testframework.models.interfaces.Postability;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Profile model representing the profiles in the application.
 * Instances can be created using the ProfileFactory class.
 *
 * @see com.testframework.factories.ProfileFactory
 */
@Getter
@Setter
public class Profile implements Friendable, Postability, Dated {

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
     * @param lastName  the last name of the profile owner
     * @param birthday  the birthday of the profile owner
     */
    public Profile(String firstName, String lastName, LocalDate birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        friendList = new ArrayList<User>();
        posts = new ArrayList<Post>();
        services = new Services();
    }

    /**
     * Constructs a Profile with the provided first name, last name, birthday, and location.
     *
     * @param firstName the first name of the profile owner
     * @param lastName  the last name of the profile owner
     * @param birthday  the birthday of the profile owner
     * @param location  the location of the profile owner
     */
    public Profile(String firstName, String lastName, LocalDate birthday, Location location) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setLocation(location);
        friendList = new ArrayList<User>();
        posts = new ArrayList<Post>();
        services = new Services();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile p = (Profile) o;
        return Objects.deepEquals(firstName, p.firstName) &&
                Objects.deepEquals(lastName, p.lastName) &&
                Dated.dateApproximatelyEquals(birthday, p.birthday) &&
                gender == p.gender &&
                location == p.location &&
                services.equals(p.services);
    }

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
