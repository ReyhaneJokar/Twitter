package model.request.tweet;

import javafx.scene.image.Image;
import model.tweet.Tweet;

import java.util.ArrayList;
import java.util.Date;

public class RetweetReq extends TweetRequest {
    public RetweetReq(String senderId, String text, Image image, int likes, int retweets, Date date, ArrayList<Tweet> replies) {
        super(senderId, TweetReqType.RETWEET, text, image, likes, retweets, date, replies);
    }
}
