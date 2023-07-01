package model.response;

import model.tweet.Tweet;

import java.util.ArrayList;

public class GetHashtagSearchRes extends GetInfoResponse{
    private final ArrayList<Tweet> resultTweets;

    public GetHashtagSearchRes(String receiverId, boolean isAccepted, String message, ArrayList<Tweet> resultTweets) {
        super(receiverId, isAccepted, message);
        this.resultTweets = resultTweets;
    }

    public ArrayList<Tweet> getResultTweets() {
        return resultTweets;
    }
}
