package model.request.user;

public class AvatarReq extends UserRequest {
    private final byte[] avatar;

    public AvatarReq(String senderId, byte[] avatar) {
        super(senderId, UserRequestType.SET_AVATAR);
        this.avatar = avatar;
    }

    public byte[] getAvatar() {
        return avatar;
    }
}
