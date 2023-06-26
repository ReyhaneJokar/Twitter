package model.tweet;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Tweet implements Serializable {
    private String text;
    private Image image;
    private int likes, retweets;
    private Date date;
    private ArrayList<Tweet> replies;

    public Tweet(String text, Image image, Date date) {
        this.text = text;
        this.image = image;
        this.date = date;
        this.likes = 0;
        this.retweets = 0;
        this.replies = new ArrayList<>();
    }

    public Tweet(String text, Image image, int likes, int retweets, Date date, ArrayList<Tweet> replies) {
        this.text = text;
        this.image = image;
        this.likes = likes;
        this.retweets = retweets;
        this.date = date;
        this.replies = replies;
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

