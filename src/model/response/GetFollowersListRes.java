package model.response;

import model.user.User;

import java.util.ArrayList;

public class GetFollowersListRes extends GetInfoResponse{
    private final ArrayList<User> followersList;

    public GetFollowersListRes(String receiverId, boolean isAccepted, String message, ArrayList<User> followersList) {
        super(receiverId, isAccepted, message);
        this.followersList = followersList;
    }

    public ArrayList<User> getFollowersList() {
        return followersList;
    }
}
