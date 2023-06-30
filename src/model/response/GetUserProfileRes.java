package model.response;

import model.profile.Profile;
import model.tweet.Tweet;
import model.user.User;

import java.util.ArrayList;
import java.util.Date;

public class GetUserProfileRes extends GetInfoResponse{
    private final String id, name, country;
    private final Date date;
    private final Profile profile;
    private final ArrayList<User> followers;
    private final ArrayList<User> following;
    private final ArrayList<Tweet> tweets;
    private final ArrayList<User> blackList;

    public GetUserProfileRes(String receiverId, boolean isAccepted, String message, String id, String name, String country, Date date, Profile profile, ArrayList<User> followers, ArrayList<User> following, ArrayList<Tweet> tweets, ArrayList<User> blackList) {
        super(receiverId, isAccepted, message);
        this.id = id;
        this.name = name;
        this.country = country;
        this.date = date;
        this.profile = profile;
        this.followers = followers;
        this.following = following;
        this.tweets = tweets;
        this.blackList = blackList;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Profile getProfile() {
        return profile;
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
}
