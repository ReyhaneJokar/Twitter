package client.resources;

import client.resources.cell.FollowersCell;
import client.resources.cell.FollowingCell;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.exception.ResponseNotFoundException;
import model.request.user.SearchReq;
import model.response.GetSearchResultRes;
import model.user.User;

import java.util.ArrayList;

public class SearchController extends Controller {

    @FXML
    private ImageView chatImageview;

    @FXML
    private ImageView homeImageview;

    @FXML
    private TextField searchTextfield;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private ImageView userImageview;

    private VBox userContent;


    @FXML
    void chatImageviewPressed(MouseEvent event) {
        //not implemented yet
    }

    @FXML
    void homeImageviewPressed(MouseEvent event) {
        changeView("home", event);
    }

    @FXML
    void searchTextfieldKeyPressed(KeyEvent event) {

        if(event.getCode() == KeyCode.ENTER){

            userContent = new VBox();
            scrollpane.setContent(userContent);
            userContent.setSpacing(10);
            userContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");

            ArrayList<User> result = new ArrayList<>();
            User senderUser = null;

            //get result of search from server
            try {
                clientThread.send(new SearchReq(clientThread.getId() , searchTextfield.getText()));

                GetSearchResultRes response = (GetSearchResultRes) clientThread.getReceiver().getResponse();
                result = response.getResultUsers();
                senderUser = response.getSenderUser();

                if(!response.isAccepted()) {
                    //closeScene();
                }
            } catch (ResponseNotFoundException e) {
                //closeScene();
            }

            if (result.size() == 0){
                searchTextfield.setText("Not found.");
                searchTextfield.setStyle("-fx-text-fill:#e20909;");
            }
            else {
                for (User user : result) {
                    if(senderUser.getFollowing().contains(user)){
                        FollowingCell followingCell = new FollowingCell(user);
                        userContent.getChildren().add(followingCell);
                    }
                    else {
                        FollowersCell followersCell = new FollowersCell(user);
                        userContent.getChildren().add(followersCell);
                    }
                }
            }
        }
    }

    @FXML
    void userImageviewPressed(MouseEvent event) {
        changeView("profile", event);
    }

}
