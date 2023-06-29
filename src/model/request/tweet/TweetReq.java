package model.request.tweet;

import model.tweet.Tweet;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class TweetReq extends TweetRequest{
    public TweetReq(String senderId , UUID uuid, String text, byte[] image, int likes, int retweets, Date date, ArrayList<Tweet> replies) {
        super(senderId, TweetReqType.TWEET, uuid , text, image, likes, retweets, date, replies);
    }
}
