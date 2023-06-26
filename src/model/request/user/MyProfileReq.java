package model.request.user;

public class MyProfileReq extends UserRequest{

    public MyProfileReq(String senderId) {
        super(senderId, UserRequestType.MY_PROFILE);
    }
}
