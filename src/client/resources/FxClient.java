package client.resources;

import client.ClientThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;

public class FxClient extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        ClientThread clientThread = ClientThread.getClientThread();
        Executors.newCachedThreadPool().execute(clientThread);

        //Stage stage = new Stage();
        clientThread.getReceiver().setLoader(new FXMLLoader(FxClient.class.getResource("fxml/Login.fxml")));
        clientThread.getReceiver().setStage(stage);
        Scene scene = new Scene(clientThread.getReceiver().getLoader().load() , 500 , 600);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}