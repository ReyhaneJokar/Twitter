package model.response;

import model.tweet.Tweet;

import java.util.ArrayList;

public class GetTimelineRes extends GetInfoResponse{
    private final ArrayList<Tweet> tweets;

    public GetTimelineRes(String receiverId, boolean isAccepted, String message, ArrayList<Tweet> tweets) {
        super(receiverId, isAccepted, message);
        this.tweets = tweets;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }
}
