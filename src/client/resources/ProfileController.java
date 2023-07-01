package client.resources;

import client.resources.cell.TweetCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.exception.ResponseNotFoundException;
import model.request.user.MyProfileReq;
import model.response.GetUserProfileRes;
import model.tweet.Tweet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
    private ImageView searchImageview;

    @FXML
    private ImageView userImageview;

    @FXML
    private Hyperlink websiteHyperlink;

    @FXML
    private Circle profileCircle;

    @FXML
    private ScrollPane scrollPane;

    private VBox tweetContent;




    @FXML
    void chatImageviewPressed(MouseEvent event) {
        //not implemented yet
    }

    @FXML
    void editProfileButtonPressed(ActionEvent event) {
        changeView("editProfile", event);
    }

    @FXML
    void followersButton(ActionEvent event) {
        changeView("follower", event);
    }

    @FXML
    void followingButtonPressed(ActionEvent event) {
        changeView("following" , event);
    }

    @FXML
    void homeImageviewPressed(MouseEvent event) {
        changeView("home", event).getController();
    }

    @FXML
    void searchImageviewPressed(MouseEvent event) {
        changeView("search", event);
    }

    @FXML
    void websiteHyperlinkPressed(ActionEvent event) {
        //not implemented yet
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tweetContent = new VBox();
        scrollPane.setContent(tweetContent);
        tweetContent.setSpacing(10);
        tweetContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");

        try {
            clientThread.send(new MyProfileReq(clientThread.getId()));

            GetUserProfileRes response = (GetUserProfileRes) clientThread.getReceiver().getResponse();

            if(!response.isAccepted()) {
                System.out.println(response.getMessage());
            }

            idLabel.setText("@" + response.getId());
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

            if (response.getProfile().getHeader() != null){
                InputStream inputStream = new ByteArrayInputStream(response.getProfile().getHeader());
                headerImageview.setImage(new Image(inputStream));
            }
            if (response.getProfile().getAvatar() != null){
                InputStream inputStream = new ByteArrayInputStream(response.getProfile().getAvatar());
                profileCircle.setFill(new ImagePattern(new Image(inputStream)));
            }


            for (Tweet tweet : response.getTweets()) {
                TweetCell tweetCell = new TweetCell(tweet);
                tweetContent.getChildren().add(tweetCell);
            }

        } catch(ResponseNotFoundException e) {
            e.printStackTrace();
        }


    }

}