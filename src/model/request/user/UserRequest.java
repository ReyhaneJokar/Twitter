package model.request.user;

import model.request.Request;
import model.request.RequestType;

public abstract class UserRequest extends Request {

    protected final UserRequestType subType;

    public UserRequest(String senderId, UserRequestType requestType) {
        super(senderId, RequestType.USER);
        this.subType = requestType;
    }

    public UserRequestType getSubType() {
        return subType;
    }
}
