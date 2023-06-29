package model.tweet;

import model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Tweet implements Serializable {
    private UUID uuid;
    private String text;
    private byte[] image;
    private int likes, retweets; //number of quotes and retweets are shown together
    private Date date;
    private ArrayList<Tweet> replies;
    private User user ;

    public Tweet(UUID uuid , String text, byte[] image, Date date , User user) {
        this.uuid = uuid;
        this.text = text;
        this.image = image;
        this.date = date;
        this.user = user;
        this.likes = 0;
        this.retweets = 0;
        this.replies = new ArrayList<>();
    }

    public Tweet(String text, byte[] image, int likes, int retweets, Date date, ArrayList<Tweet> replies , User user , UUID uuid) {
        this.uuid = uuid;
        this.text = text;
        this.image = image;
        this.likes = likes;
        this.retweets = retweets;
        this.date = date;
        this.replies = replies;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public byte[] getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getUser() {
        return user;
    }

    public int getLikes() {
        return likes;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public ArrayList<Tweet> getReplies() {
        return replies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tweet)) return false;
        Tweet tweet = (Tweet) o;
        return getLikes() == tweet.getLikes() && getRetweets() == tweet.getRetweets() && Objects.equals(text, tweet.text) && Objects.equals(image, tweet.image) && Objects.equals(date, tweet.date) && Objects.equals(getReplies(), tweet.getReplies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, image, getLikes(), getRetweets(), date, getReplies());
    }
}

