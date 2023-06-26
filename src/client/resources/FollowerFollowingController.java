package client.resources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FollowerFollowingController extends Controller implements Initializable {

    @FXML
    private Button followersButton;

    @FXML
    private Button followingButton;

    @FXML
    private ScrollPane scrollPane;

    private VBox userContent;

    @FXML
    void followersButtonPressed(ActionEvent event) {

    }

    @FXML
    void followingButtonPressed(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userContent = new VBox();
        scrollPane.setContent(userContent);
        userContent.setSpacing(10);
        userContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");

        //get followers from server

//        for (User user : )
//        {
//            FollowersCell followersCell = new FollowersCell(user);
//            userContent.getChildren().add(followersCell);
//        }
    }
}

