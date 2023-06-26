package connect.api;

import connect.api.server.ConnectionHandler;
import connect.api.server.UserThread;
import model.Response;
import model.exception.UserThreadNotFoundException;

public class Sender {
    private ConnectionHandler connectionHandler;
    private static Sender sender;

    protected static Sender getSender() {
        if(sender == null) {
            sender = new Sender();
        }
        return sender;
    }

    private Sender()
    {
        connectionHandler = ConnectionHandler.getConnectionHandler();
    }
    //sends a response to client
    protected void sendResponse(Response response) throws UserThreadNotFoundException {
        UserThread userThread = connectionHandler.getUserThread(response.getReceiverId());

        if(null != userThread) {
            userThread.send(response);
        }
        else
        {
            throw new UserThreadNotFoundException("failed to send response , server thread not found.");
        }
    }
    protected void sendResponse(Response response , UserThread userThread) throws UserThreadNotFoundException {
        if(null != userThread) {
            userThread.send(response);
        }
        else {
            throw new UserThreadNotFoundException("failed to send response , server thread not found.");
        }
    }

}
