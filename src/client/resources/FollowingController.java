package client.resources;

import client.resources.cell.FollowingCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.exception.ResponseNotFoundException;
import model.request.user.FollowingListReq;
import model.response.GetFollowingListRes;
import model.user.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FollowingController extends Controller implements Initializable {

    @FXML
    private ImageView backImageview;

    @FXML
    private Button followersButton;

    @FXML
    private ScrollPane scrollPane;

    private VBox userContent;

    @FXML
    void followersButtonPressed(ActionEvent event) {
        changeView("follower", event);
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

        //get followers from server
        ArrayList<User> following = new ArrayList<>();

        try {
            clientThread.send(new FollowingListReq(clientThread.getId()));

            GetFollowingListRes response = (GetFollowingListRes) clientThread.getReceiver().getResponse();
            following = response.getFollowingList();

            if(!response.isAccepted()) {
                System.out.println(response.getMessage());
            }
        } catch (ResponseNotFoundException e) {
            e.printStackTrace();
        }

        for (User user : following) {
            FollowingCell followingCell = new FollowingCell(user);
            userContent.getChildren().add(followingCell);
        }
    }
}
