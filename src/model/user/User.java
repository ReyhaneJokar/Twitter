package model.user;

import model.profile.Profile;
import model.tweet.Tweet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class User implements Serializable {
    private  String id, name, lastname, email, phone, password, country, birthdate;
    private Profile profile;
    private Date joinDate;
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private ArrayList<Tweet> tweets;
    private ArrayList<User> blackList;
    private ArrayList<Tweet> timeline;

    public User(String id, String name, String lastname, String email, String phone, String password, String country, String birthdate) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.country = country;
        this.birthdate = birthdate;
        this.joinDate = new Date();
        profile = new Profile();
        followers = new ArrayList<>();
        following = new ArrayList<>();
        tweets = new ArrayList<>();
        blackList = new ArrayList<>();
        timeline = new ArrayList<>();

    }

    public User(String id , String password){
        this.id = id;
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }


    public Profile getProfile() {
        return profile;
    }

    public String getId() {
        return id;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public ArrayList<User> getBlackList() {
        return blackList;
    }

    public ArrayList<Tweet> getTimeline() {
        return timeline;
    }
}

