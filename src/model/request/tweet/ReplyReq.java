package model.request.tweet;

import javafx.scene.image.Image;
import model.tweet.Tweet;

import java.util.ArrayList;
import java.util.Date;

public class ReplyReq extends TweetRequest {
    private final String targetId;
    private final String replyText;
    private final Image replyImage;
    private final int replyLikes, replyRetweets;
    private final Date replyDate;
    private final ArrayList<Tweet> replyReplies;

    public ReplyReq(String senderId, String text, Image image, int likes, int retweets, Date date, ArrayList<Tweet> replies, String targetId , String replyText, Image replyImage, int replyLikes, int replyRetweets, Date replyDate, ArrayList<Tweet> replyReplies) {
        super(senderId, TweetReqType.REPLY, text, image, likes, retweets, date, replies);
        this.targetId = targetId;
        this.replyText = replyText;
        this.replyImage = replyImage;
        this.replyLikes = replyLikes;
        this.replyRetweets = replyRetweets;
        this.replyDate = replyDate;
        this.replyReplies = replyReplies;
    }

    public String getTargetId() {
        return targetId;
    }

    public String getReplyText() {
        return replyText;
    }

    public Image getReplyImage() {
        return replyImage;
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
