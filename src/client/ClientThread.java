package client;

import model.Config;
import model.Response;
import model.exception.InvalidObjectException;
import model.request.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * A singleton class
 */

public class ClientThread implements Runnable{
    private static ClientThread clientThread;
    private final ClientReceiver receiver;
    //client id
    private String id;
    //socket between client and server
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    Config config = Config.getInstance();

    private ClientThread() {
        receiver = new ClientReceiver();
        try {
            socket = new Socket("127.0.0.1" , config.getPORT());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ClientThread getClientThread(){
        if(clientThread == null) {
            clientThread = new ClientThread();
        }
        return clientThread;
    }

    //beside the main thread, client has a second thread
    // which checks the responses coming from server
    @Override
    public void run() {
        while(socket.isConnected())
        {
            try {
                receiver.getInput((Response) inputStream.readObject());
            } catch (InvalidObjectException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(Request request)
    {
        try {
            outputStream.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ClientReceiver getReceiver() {
        return receiver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
