package model.request.user;


public class UnFollowReq extends UserRequest {
    //id of person who is going to be unfollowed
    private final String targetId;

    public UnFollowReq(String senderId, String targetId) {
        super(senderId, UserRequestType.UNFOLLOW);
        this.targetId = targetId;
    }

    public String getTargetId() {
        return targetId;
    }
}
