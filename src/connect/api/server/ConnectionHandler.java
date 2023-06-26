package connect.api.server;

import model.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ConnectionHandler {
    Config config = Config.getInstance();

    private static ConnectionHandler connectionHandler;
    //connection of clients
    //maps ids to user threads of each client
    private HashMap<String , UserThread> connections;

    private ConnectionHandler() {
        connections = new HashMap<>();
    }

    public static ConnectionHandler getConnectionHandler()
    {
        if(connectionHandler == null)
        {
            connectionHandler = new ConnectionHandler();
        }
        return connectionHandler;
    }

    public void run()
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("server is running on port : 8080\n");

        try(ServerSocket socket = new ServerSocket(config.getPORT()))
        {
            while(true)
            {
                Socket clientSocket = socket.accept();
                UserThread userThread = new UserThread(clientSocket);
                executorService.execute(userThread);
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public UserThread getUserThread(String id)
    {
        return connections.get(id);
    }

    public void addConnection(String id , UserThread userThread)
    {
        connections.put(id, userThread);
        System.out.println("connection with id :" + id + " added.");
    }

    public void removeConnection(String id)
    {
        connections.remove(id);
        System.out.println("connection with id :" + id + " removed.");
    }
}
