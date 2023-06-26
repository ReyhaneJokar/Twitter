package model.request.user;

import javafx.scene.image.Image;

public class AvatarReq extends UserRequest {
    private final Image avatar;

    public AvatarReq(String senderId, Image avatar) {
        super(senderId, UserRequestType.SET_AVATAR);
        this.avatar = avatar;
    }

    public Image getAvatar() {
        return avatar;
    }
}
