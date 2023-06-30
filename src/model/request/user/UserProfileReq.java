package model.request.user;

import java.util.UUID;

public class UserProfileReq extends UserRequest{
    private final UUID tweetUUID;

    public UserProfileReq(String senderId, UUID tweetUUID) {
        super(senderId, UserRequestType.USER_PROFILE);
        this.tweetUUID = tweetUUID;
    }

    public UUID getTweetUUID() {
        return tweetUUID;
    }
}
