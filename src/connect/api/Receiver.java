package connect.api;

import model.exception.InvalidObjectException;
import model.exception.InvalidTypeException;
import model.request.Authentication.AuthenticationReq;
import model.request.Request;
import model.request.tweet.TweetRequest;
import model.request.user.UserRequest;

public class Receiver {
    private static Receiver requestReceiver;
    private final UserApi userApi;
    private final AuthenticationApi authenticationApi;
    private final TweetApi tweetApi;

    public static Receiver getReceiver() {
        if(requestReceiver == null) {
            requestReceiver = new Receiver();
        }
        return requestReceiver;
    }

    private Receiver() {
        userApi = new UserApi();
        authenticationApi = new AuthenticationApi();
        tweetApi = new TweetApi();
    }

     // receives and handles a request or message
    public void receive(Request request) throws InvalidTypeException, InvalidObjectException {
        try {
            getRequest(request);
        } catch (ClassCastException e) {
                throw new InvalidObjectException();
        }
    }

    private void getRequest(Request request) throws InvalidTypeException {
        switch (request.getRequestType()) {
            case USER:
                userApi.getRequest((UserRequest) request);
                break;
            case AUTHENTICATION:
                authenticationApi.getRequest((AuthenticationReq) request);
                break;
            case TWEET:
                tweetApi.getRequest((TweetRequest) request);
                break;
            case PRIVATE_CHAT:
                System.out.println("not implemented yet");
                break;
            default:
                throw new InvalidTypeException();
        }
    }

}
