package model.response;

import model.user.User;

import java.util.ArrayList;

public class GetFollowingListRes extends GetInfoResponse{
    private final ArrayList<User> followingList;

    public GetFollowingListRes(String receiverId, boolean isAccepted, String message, ArrayList<User> followingList) {
        super(receiverId, isAccepted, message);
        this.followingList = followingList;
    }

    public ArrayList<User> getFollowingList() {
        return followingList;
    }
}
