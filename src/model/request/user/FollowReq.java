package model.request.user;

public class FollowReq extends UserRequest {
    //id of person who is going to be followed
    private final String targetId;

    public FollowReq(String senderId, String targetId) {
        super(senderId, UserRequestType.FOLLOW);
        this.targetId = targetId;
    }

    public String getTargetId() {
        return targetId;
    }
}
