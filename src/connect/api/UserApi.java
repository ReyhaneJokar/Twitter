package connect.api;

import connect.service.UserService;
import model.Response;
import model.exception.InvalidTypeException;
import model.exception.UserThreadNotFoundException;
import model.request.user.*;


public class UserApi {
    private final UserService service;
    private final Sender sender;

    protected UserApi()
    {
        service = new UserService();
        sender = Sender.getSender();
    }


    protected void getRequest(UserRequest request) throws InvalidTypeException {
        switch(request.getSubType()) {
            case MY_PROFILE:
                get_profile((MyProfileReq) request);
                break;
            case SET_AVATAR:
                set_avatar((AvatarReq) request);
                break;
            case SET_HEADER:
                set_header((HeaderReq) request);
                break;
            case SET_BIO:
                set_bio((BioReq) request);
                break;
            case FOLLOW:
                follow((FollowReq) request);
                break;
            case UNFOLLOW:
                unfollow((UnFollowReq) request);
                break;
            case SHOW_TIMELINE:
                show_timeline((TimelineReq) request);
                break;
            case BLOCK:
                blockUser((BlockReq) request);
                break;
            case UNBLOCK:
                unblock((UnblockReq) request);
                break;
            case GET_FOLLOWERS:
                get_followers((FollowersListReq) request);
                break;
            case GET_FOLLOWING:
                get_following((FollowingListReq) request);
                break;
            default:
                throw new InvalidTypeException();
        }
    }


    private void get_followers(FollowersListReq request){
        sendResponse(service.getFollowersList(request));
    }

    private void get_following(FollowingListReq request){
        sendResponse(service.getFollowingList(request));
    }

    private void get_profile(MyProfileReq request){
        sendResponse(service.get_profile(request));
    }

    private void blockUser(BlockReq request)
    {
        handleResponse(service.block_user(request) , request);
    }

    private void set_avatar(AvatarReq request)
    {
        handleResponse(service.set_avatar(request) , request);
    }

    private void set_header(HeaderReq request)
    {
        handleResponse(service.set_header(request) , request);
    }

    private void set_bio(BioReq request)
    {
        handleResponse(service.set_bio(request) , request);
    }

    private void follow(FollowReq request)
    {
        handleResponse(service.follow(request) , request);
    }

    private void unfollow(UnFollowReq request)
    {
        handleResponse(service.unfollow(request) , request);
    }

    private void show_timeline(TimelineReq request)
    {
        handleResponse(service.show_timeline(request) , request);
    }

    private void unblock(UnblockReq request)
    {
        handleResponse(service.unblock(request) , request);
    }

    private void handleResponse(Response response , UserRequest request)
    {
//        try {
//            sender.sendResponse(response , request.getServerThread());
//        }
//        catch (ServerThreadNotFoundException e) {
//            e.printStackTrace();
//        }


    }

    private void sendResponse(Response response)
    {
        try {
            sender.sendResponse(response);
        } catch (UserThreadNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }







}
