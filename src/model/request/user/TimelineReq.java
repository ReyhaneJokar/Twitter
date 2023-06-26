package model.request.user;



public class TimelineReq extends UserRequest {

    public TimelineReq(String senderId) {
        super(senderId, UserRequestType.SHOW_TIMELINE);
    }
}
