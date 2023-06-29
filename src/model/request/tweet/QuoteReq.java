package model.request.tweet;

import model.tweet.Tweet;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class QuoteReq extends TweetRequest {
    private final String quote;

    public QuoteReq(String senderId, UUID uuid , String text, byte[] image, int likes, int retweets, Date date, ArrayList<Tweet> replies, String quote) {
        super(senderId, TweetReqType.QUOTE , uuid, text, image, likes, retweets, date, replies);
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

}
