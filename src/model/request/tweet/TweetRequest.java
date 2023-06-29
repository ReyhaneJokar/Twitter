package model.request.tweet;

import model.request.Request;
import model.request.RequestType;
import model.tweet.Tweet;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class TweetRequest extends Request {

    protected final TweetReqType subType;
    private final UUID uuid;
    private final String text;
    private final byte[] image;
    private final int likes, retweets;
    private final Date date;
    private final ArrayList<Tweet> replies;


    public TweetRequest(String senderId, TweetReqType subType , UUID uuid , String text, byte[] image, int likes, int retweets, Date date, ArrayList<Tweet> replies) {
        super(senderId, RequestType.TWEET);
        this.subType = subType;
        this.uuid = uuid;
        this.text = text;
        this.image = image;
        this.likes = likes;
        this.retweets = retweets;
        this.date = date;
        this.replies = replies;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getText() {
        return text;
    }

    public byte[] getImage() {
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
