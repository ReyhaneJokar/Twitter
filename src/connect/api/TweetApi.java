package connect.api;

import connect.service.TweetService;
import model.Response;
import model.exception.InvalidTypeException;
import model.exception.UserThreadNotFoundException;
import model.request.tweet.*;


public class TweetApi {

    private final TweetService service;
    //sender object to send responses to client
    private final Sender sender;

    protected TweetApi() {
        service = new TweetService();
        sender = Sender.getSender();
    }


    protected void getRequest(TweetRequest tweetRequest) throws InvalidTypeException {
        switch(tweetRequest.getSubType()) {
            case TWEET:
                tweet((TweetReq) tweetRequest);
                break;
            case RETWEET:
                retweet((RetweetReq) tweetRequest);
                break;
            case QUOTE:
            quote((QuoteReq) tweetRequest);
            break;
            case REPLY:
                reply((ReplyReq) tweetRequest);
                break;
            case LIKE:
                like((LikeTweetReq) tweetRequest);
                break;
            default:
                throw new InvalidTypeException();
        }

    }

    private void like(LikeTweetReq tweetRequest){
        handleResponse(service.like(tweetRequest) , tweetRequest);
    }


    private void tweet(TweetReq tweetRequest)
    {
        handleResponse(service.tweet(tweetRequest) , tweetRequest);
    }

    private void retweet(RetweetReq tweetRequest)
    {
        handleResponse(service.retweet(tweetRequest) , tweetRequest);
    }

    private void quote(QuoteReq tweetRequest)
    {
        handleResponse(service.quote(tweetRequest) , tweetRequest);
    }

    private void reply(ReplyReq tweetRequest)
    {
        handleResponse(service.reply(tweetRequest) , tweetRequest);
    }


    private void handleResponse(Response response , TweetRequest request)
    {
        sendResponse(response);
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
