package com.testframework.models;

import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Location;
import com.testframework.models.interfaces.Friendable;
import com.testframework.models.interfaces.Postability;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Profile implements Friendable, Postability {

    public Profile() {
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

    //############# GETTERS #########
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public Location getLocation() {
        return location;
    }

    public List<User> getFriendList() {
        return new ArrayList<User>(friendList);
    }

    public List<Post> getPosts() {
        return new ArrayList<Post>(posts);
    }

    public String getBio() {
        return bio;
    }

    public double getWeeklyAvailability() {
        return weeklyAvailability;
    }

    //############# SETTERS #########
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setWeeklyAvailability(double weeklyAvailability) {
        this.weeklyAvailability = weeklyAvailability;
    }
}