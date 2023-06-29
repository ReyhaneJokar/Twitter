package model.request.tweet;

import model.tweet.Tweet;
import model.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class QuoteReq extends TweetRequest {
    private final String quote;

    public QuoteReq(String senderId, UUID uuid , String text, byte[] image, int likes, int retweets, Date date, ArrayList<Tweet> replies, String quote, User user) {
        super(senderId, TweetReqType.QUOTE , uuid, text, image, likes, retweets, date, replies , user);
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

}
