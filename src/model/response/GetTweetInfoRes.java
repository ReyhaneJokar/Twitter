package model.response;

import model.tweet.Tweet;

public class GetTweetInfoRes extends GetInfoResponse{
    private final Tweet tweet;

    public GetTweetInfoRes(String receiverId, boolean isAccepted, String message, Tweet tweet) {
        super(receiverId, isAccepted, message);
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }
}
