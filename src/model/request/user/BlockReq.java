package model.request.user;

public class BlockReq extends UserRequest {
    //id of person who is going to be blocked
    private final String targetId;

    public BlockReq(String senderId, String targetId) {
        super(senderId, UserRequestType.BLOCK);
        this.targetId = targetId;
    }

    public String getTargetId() {
        return targetId;
    }
}
