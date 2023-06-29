package model.request.tweet;

import model.tweet.Tweet;
import model.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ReplyReq extends TweetRequest {
    private final UUID replyuuid;
    private final String targetId;
    private final String replyText;
    private final int replyLikes, replyRetweets;
    private final Date replyDate;
    private final ArrayList<Tweet> replyReplies;

    public ReplyReq(String senderId, UUID uuid, String text, byte[] image, int likes, int retweets, Date date, ArrayList<Tweet> replies , UUID replyuuid, String targetId , String replyText, int replyLikes, int replyRetweets, Date replyDate, ArrayList<Tweet> replyReplies, User user) {
        super(senderId, TweetReqType.REPLY , uuid, text, image, likes, retweets, date, replies , user);
        this.replyuuid = replyuuid;
        this.targetId = targetId;
        this.replyText = replyText;
        this.replyLikes = replyLikes;
        this.replyRetweets = replyRetweets;
        this.replyDate = replyDate;
        this.replyReplies = replyReplies;
    }

    public UUID getReplyuuid() {
        return replyuuid;
    }

    public String getTargetId() {
        return targetId;
    }

    public String getReplyText() {
        return replyText;
    }

    public int getReplyLikes() {
        return replyLikes;
    }

    public int getReplyRetweets() {
        return replyRetweets;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public ArrayList<Tweet> getReplyReplies() {
        return replyReplies;
    }
}
