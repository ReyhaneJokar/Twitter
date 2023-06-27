package model.response;

import model.user.User;

import java.util.ArrayList;

public class GetSearchResultRes extends GetInfoResponse{
    private final ArrayList<User> resultUsers;
    private final User senderUser;

    public GetSearchResultRes(String receiverId, boolean isAccepted, String message, ArrayList<User> resultUsers , User senderUser) {
        super(receiverId, isAccepted, message);
        this.resultUsers = resultUsers;
        this.senderUser = senderUser;
    }

    public ArrayList<User> getResultUsers() {
        return resultUsers;
    }

    public User getSenderUser() {
        return senderUser;
    }
}
