package model.request.user;


public class HeaderReq extends UserRequest {
    private final byte[] header;

    public HeaderReq(String senderId, byte[] header) {
        super(senderId, UserRequestType.SET_HEADER);
        this.header = header;
    }

    public byte[] getHeader() {
        return header;
    }
}
