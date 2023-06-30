package model.request.user;

import java.util.UUID;

public class TweetInfoReq extends UserRequest{
    private final UUID uuid;

    public TweetInfoReq(String senderId, UUID uuid) {
        super(senderId, UserRequestType.TWEET_INFO);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
