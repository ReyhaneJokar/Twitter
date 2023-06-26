package model.request.user;


import javafx.scene.image.Image;

public class HeaderReq extends UserRequest {
    private final Image header;

    public HeaderReq(String senderId, Image header) {
        super(senderId, UserRequestType.SET_HEADER);
        this.header = header;
    }

    public Image getHeader() {
        return header;
    }
}
