package model.request.Authentication;


import connect.api.server.UserThread;

public class LogInReq extends AuthenticationReq {

    public LogInReq(String senderId, String password , UserThread userThread) {
        super(senderId, AuthenticationReqType.LOGIN, password , userThread);
    }

}
