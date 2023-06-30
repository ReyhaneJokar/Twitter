package client.resources;

import client.ClientThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    protected static final ClientThread clientThread = ClientThread.getClientThread();

    public static FXMLLoader changeView(String newView, ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.class.getResource("fxml/" + newView + ".fxml"));

            clientThread.getReceiver().setLoader(loader);
            Parent homeParent = loader.load();
            Stage window = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Scene scene = ((Button)event.getSource()).getScene();
            scene.setRoot(homeParent);

            window.setMinHeight(600);
            window.setMinWidth(500);
            window.setScene(scene);
            clientThread.getReceiver().setStage(window);
            return loader;

        } catch(IOException e){
            e.printStackTrace();
            System.out.println("could not load " + newView + ".fxml class");
        }
        return null;
    }

    public FXMLLoader changeView(String newView, MouseEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/" + newView + ".fxml"));

            clientThread.getReceiver().setLoader(loader);
            Parent homeParent = loader.load();
            Stage window = (Stage) ((ImageView)event.getSource()).getScene().getWindow();
            Scene scene = ((ImageView)event.getSource()).getScene();
            scene.setRoot(homeParent);

            window.setMinHeight(600);
            window.setMinWidth(500);
            window.setScene(scene);
            clientThread.getReceiver().setStage(window);
            return loader;

        } catch(IOException e){
            e.printStackTrace();
            System.out.println("could not load " + newView + ".fxml class");
        }
        return null;
    }
}
