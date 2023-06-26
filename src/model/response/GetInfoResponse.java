package model.response;

import model.Response;

public class GetInfoResponse extends Response {
    public GetInfoResponse(String receiverId, boolean isAccepted, String message) {
        super(receiverId, isAccepted, message);
    }
}
