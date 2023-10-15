package com.testframework.models;

import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Location;
import com.testframework.models.interfaces.Friendable;
import com.testframework.models.interfaces.Postability;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Profile implements Friendable, Postability {

    public Profile() {
        friendList = new ArrayList<User>();
        posts = new ArrayList<Post>();
    }

    public Profile(String firstName, String lastName, Date birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        friendList = new ArrayList<User>();
        posts = new ArrayList<Post>();
    }

    private String firstName;
    private String lastName;
    private Date birthday;
    private Gender gender;
    private Location location;
    private ArrayList<User> friendList;
    private ArrayList<Post> posts;
    private String bio;
    private double weeklyAvailability;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile p = (Profile) o;

        return firstName.equals(p.firstName)
                && lastName.equals(p.lastName)
                && birthday.equals(p.birthday)
                && gender.equals(p.gender)
                && location.equals(p.location)
                && friendList.equals(p.friendList)
                && posts.equals(p.posts)
                && bio.equals(p.bio)
                && weeklyAvailability==p.weeklyAvailability;
    }

    //############# LISTS #########
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

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
