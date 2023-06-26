package model.request.tweet;

import javafx.scene.image.Image;
import model.tweet.Tweet;

import java.util.ArrayList;
import java.util.Date;

public class TweetReq extends TweetRequest{
    public TweetReq(String senderId, String text, Image image, int likes, int retweets, Date date, ArrayList<Tweet> replies) {
        super(senderId, TweetReqType.TWEET, text, image, likes, retweets, date, replies);
    }
}
