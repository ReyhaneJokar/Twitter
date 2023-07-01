package client.resources;

import client.resources.cell.FollowersCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.exception.ResponseNotFoundException;
import model.request.user.FollowersListReq;
import model.response.GetFollowersListRes;
import model.user.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FollowerController extends Controller implements Initializable {

    @FXML
    private ImageView backImageview;

    @FXML
    private Button followingButton;

    @FXML
    private ScrollPane scrollPane;

    private VBox userContent;

    @FXML
    void followingButtonPressed(ActionEvent event) {
        changeView("following", event);
    }

    @FXML
    void backImageviewPressed(MouseEvent event) {
        changeView("profile", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userContent = new VBox();
        scrollPane.setContent(userContent);
        userContent.setSpacing(10);
        userContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");

        ArrayList<User> followers = new ArrayList<>();

        //get followers from server
        try {
            clientThread.send(new FollowersListReq(clientThread.getId()));

            GetFollowersListRes response = (GetFollowersListRes) clientThread.getReceiver().getResponse();
            followers = response.getFollowersList();

            if(!response.isAccepted()) {
                System.out.println(response.getMessage());
            }
        } catch (ResponseNotFoundException e) {
            e.printStackTrace();
        }

        for (User user : followers) {
            FollowersCell followersCell = new FollowersCell(user);
            userContent.getChildren().add(followersCell);
        }
    }
}

