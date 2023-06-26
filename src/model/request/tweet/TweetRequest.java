package model.request.tweet;

import javafx.scene.image.Image;
import model.request.Request;
import model.request.RequestType;
import model.tweet.Tweet;

import java.util.ArrayList;
import java.util.Date;

public abstract class TweetRequest extends Request {

    protected final TweetReqType subType;
    private final String text;
    private final Image image;
    private final int likes, retweets;
    private final Date date;
    private final ArrayList<Tweet> replies;


    public TweetRequest(String senderId, TweetReqType subType, String text, Image image, int likes, int retweets, Date date, ArrayList<Tweet> replies) {
        super(senderId, RequestType.TWEET);
        this.subType = subType;
        this.text = text;
        this.image = image;
        this.likes = likes;
        this.retweets = retweets;
        this.date = date;
        this.replies = replies;
    }

    public String getText() {
        return text;
    }

    public Image getImage() {
        return image;
    }

    public int getLikes() {
        return likes;
    }

    public int getRetweets() {
        return retweets;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Tweet> getReplies() {
        return replies;
    }

    public TweetReqType getSubType() {
        return subType;
    }
}
