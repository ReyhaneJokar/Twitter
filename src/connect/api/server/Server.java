package connect.api.server;

public class Server {
    public static void main(String[] args) {
        ConnectionHandler connectionHandler = ConnectionHandler.getConnectionHandler();
        connectionHandler.run();
    }
}