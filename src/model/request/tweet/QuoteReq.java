package model.request.tweet;

import javafx.scene.image.Image;
import model.tweet.Tweet;

import java.util.ArrayList;
import java.util.Date;

public class QuoteReq extends TweetRequest {
    private final String quote;

    public QuoteReq(String senderId, String text, Image image, int likes, int retweets, Date date, ArrayList<Tweet> replies, String quote) {
        super(senderId, TweetReqType.QUOTE, text, image, likes, retweets, date, replies);
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

}
