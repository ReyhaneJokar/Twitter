package model.request;

import java.io.Serializable;

public abstract class Request implements Serializable {
    //id of user who sent the request
    private final String senderId;
    private final RequestType requestType;

    public Request(String senderId, RequestType requestType)
    {
        this.senderId = senderId;
        this.requestType = requestType;
    }

    public String getSenderId()
    {
        return senderId;
    }

    public RequestType getRequestType()
    {
        return requestType;
    }
}
