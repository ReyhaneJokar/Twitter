package client.resources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.exception.ResponseNotFoundException;
import model.request.user.MyProfileReq;
import model.response.GetUserProfileRes;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends Controller implements Initializable {

    @FXML
    private Label bioLabel;

    @FXML
    private ImageView chatImageview;

    @FXML
    private Label dateLabel;

    @FXML
    private Button editProfileButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Button followersButton;

    @FXML
    private Label followersNumLabel;

    @FXML
    private Button followingButton;

    @FXML
    private Label followingNumLabel;

    @FXML
    private ImageView headerImageview;

    @FXML
    private ImageView homeImageview;

    @FXML
    private Label idLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView profileImageview;

    @FXML
    private ImageView searchImageview;

    @FXML
    private ImageView userImageview;

    @FXML
    private Hyperlink websiteHyperlink;

    @FXML
    void chatImageviewPressed(MouseEvent event) {

    }

    @FXML
    void editProfileButtonPressed(ActionEvent event) {
        changeView("editProfile", event);
    }

    @FXML
    void followersButton(ActionEvent event) {

    }

    @FXML
    void followingButtonPressed(ActionEvent event) {

    }

    @FXML
    void homeImageviewPressed(MouseEvent event) {
        changeView("home", event).getController();
    }

    @FXML
    void searchImageviewPressed(MouseEvent event) {

    }

    @FXML
    void userImageviewPressed(MouseEvent event) {

    }

    @FXML
    void websiteHyperlinkChanged(KeyEvent event) {

    }

    @FXML
    void websiteHyperlinkPressed(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            clientThread.send(new MyProfileReq(clientThread.getId()));

            GetUserProfileRes response = (GetUserProfileRes) clientThread.getReceiver().getResponse();

            if(!response.isAccepted())
            {
                //closeScene();
            }

            idLabel.setText(response.getId());
            nameLabel.setText(response.getName());
            bioLabel.setText(response.getProfile().getBio());
            locationLabel.setText(response.getProfile().getLocation());
            if (response.getProfile().getWebsite() != null){
                websiteHyperlink.setText(response.getProfile().getWebsite());
            }
            followingNumLabel.setText(String.valueOf(response.getFollowing().size()));
            followersNumLabel.setText(String.valueOf(response.getFollowers().size()));
            String date = response.getDate().toString().split(" ")[5];
            date += " " + response.getDate().toString().split(" ")[1];
            dateLabel.setText(date);

            headerImageview.setImage(response.getProfile().getHeader());
            profileImageview.setImage(response.getProfile().getAvatar());

        } catch(ResponseNotFoundException e) {
            //closeScene();
        }

    }

//    private void closeScene()
//    {
//        Stage stage = (Stage)pane.getScene().getWindow();
//        stage.hide();
//    }
}