package connect.api.server;

import connect.api.Receiver;
import model.Response;
import model.exception.InvalidObjectException;
import model.exception.InvalidTypeException;
import model.request.Authentication.AuthenticationReq;
import model.request.Authentication.LogInReq;
import model.request.Authentication.SignUpReq;
import model.request.Request;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;


public class UserThread implements Runnable{
    private String userId; //user who is connected to this thread
    private final Receiver receiver;
    private final Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;


    public UserThread(Socket socket) {
        this.socket = socket;
        receiver = Receiver.getReceiver();
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(socket.isConnected())
        {
            try{
                Request input = (Request) inputStream.readObject();
                if(input instanceof SignUpReq || input instanceof LogInReq)
                {
                    ((AuthenticationReq) input).setUserThread(this);
                }
                receiver.receive(input);
            }catch (SocketException | EOFException e) {
                ConnectionHandler.getConnectionHandler().removeConnection(userId);
                return;
            } catch (IOException | ClassNotFoundException | InvalidTypeException | InvalidObjectException e) {
                e.printStackTrace();
                return;
            }
        }
        ConnectionHandler.getConnectionHandler().removeConnection(userId);
    }

    //sends a response to client
    public void send(Response response) {
        try {
            outputStream.writeObject(response);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void verified(String id) {
        //setting verified id
        setUserId(id);

        //look for messages of user
        //receiver.getUnreadMessages(id);
        /**
         * inja bayad bere timeline ro neshoon bede
         */
    }

    //when authentication is accepted we should set the user thread id
    public void setUserId(String userId) {
        this.userId = userId;
    }
}

