package model.user;

import model.profile.Profile;
import model.tweet.Tweet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


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

    public String getBirthdate() {
        return birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getLastname(), user.getLastname()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getCountry(), user.getCountry()) && Objects.equals(getBirthdate(), user.getBirthdate()) && Objects.equals(getProfile(), user.getProfile()) && Objects.equals(getJoinDate(), user.getJoinDate()) && Objects.equals(getFollowers(), user.getFollowers()) && Objects.equals(getFollowing(), user.getFollowing()) && Objects.equals(getTweets(), user.getTweets()) && Objects.equals(getBlackList(), user.getBlackList()) && Objects.equals(getTimeline(), user.getTimeline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastname(), getEmail(), getPhone(), getPassword(), getCountry(), getBirthdate(), getProfile(), getJoinDate(), getFollowers(), getFollowing(), getTweets(), getBlackList(), getTimeline());
    }
}

