package model.request.user;

public class FollowingListReq extends UserRequest{
    public FollowingListReq(String senderId) {
        super(senderId, UserRequestType.GET_FOLLOWING);
    }
}
