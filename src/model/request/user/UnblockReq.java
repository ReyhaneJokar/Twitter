package model.request.user;

public class UnblockReq extends UserRequest {
    //id of person who is going to be unblocked
    private final String targetId;

    public UnblockReq(String senderId, String targetId) {
        super(senderId, UserRequestType.UNBLOCK);
        this.targetId = targetId;
    }

    public String getTargetId() {
        return targetId;
    }
}
