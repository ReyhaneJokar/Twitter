package model.request.Authentication;

import connect.api.server.UserThread;
import model.request.Request;
import model.request.RequestType;

abstract public class AuthenticationReq extends Request {

    private final AuthenticationReqType subType;
    private final String password;
    //server thread that this request comes from
    private UserThread userThread;

    public AuthenticationReq(String senderId, AuthenticationReqType requestType, String password , UserThread userThread) {
        super(senderId, RequestType.AUTHENTICATION);
        subType = requestType;
        this.password = password;
        this.userThread = userThread;
    }

    public AuthenticationReqType getSubType() {
        return subType;
    }

    public String getPassword() {
        return password;
    }

    public UserThread getUserThread() {
        return userThread;
    }

    public void setUserThread(UserThread userThread){
        this.userThread = userThread;
    }
}
