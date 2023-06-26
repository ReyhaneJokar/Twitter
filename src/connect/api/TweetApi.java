package connect.api;

import connect.service.TweetService;
import model.exception.InvalidTypeException;
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
            default:
                throw new InvalidTypeException();
        }

    }


    private void tweet(TweetReq tweetRequest)
    {
        // check the database for this request and then send response to the client
    }

    private void retweet(RetweetReq tweetRequest)
    {
        // check the database for this request and then send response to the client
    }

    private void quote(QuoteReq tweetRequest)
    {
        // check the database for this request and then send response to the client
    }

    private void reply(ReplyReq tweetRequest)
    {
        // check the database for this request and then send response to the client
    }
}
