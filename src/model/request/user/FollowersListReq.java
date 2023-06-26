package model.request.user;

public class FollowersListReq extends UserRequest{
    public FollowersListReq(String senderId) {
        super(senderId, UserRequestType.GET_FOLLOWERS);
    }
}
