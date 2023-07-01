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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.Response;
import model.exception.ResponseNotFoundException;
import model.request.user.*;
import model.response.GetUserProfileRes;
import model.tweet.Tweet;
import model.user.User;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class otherUsersProfileController extends Controller implements Initializable {

    @FXML
    private Label bioLabel;

    @FXML
    private Button blockUnblockButton;

    @FXML
    private ImageView chatImageview;

    @FXML
    private Label dateLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Button followUnfollowButton;

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
    private Circle profileCircle;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView searchImageview;

    @FXML
    private ImageView userImageview;

    @FXML
    private Hyperlink websiteHyperlink;

    private VBox tweetContent;

    private User user;


    @FXML
    void blockUnblockButtonPressed(ActionEvent event) {

        if (blockUnblockButton.getText().equals("Block")){

            try {
                clientThread.send(new BlockReq(clientThread.getId() , user.getId()));

                Response response = clientThread.getReceiver().getResponse();

                blockUnblockButton.setText("Blocked");
                blockUnblockButton.setTextFill(Paint.valueOf("white"));
                blockUnblockButton.setStyle("-fx-background-color:#0da5f3;");

            } catch (ResponseNotFoundException e) {
                e.printStackTrace();
            }

        }
        else {
            try {
                clientThread.send(new UnblockReq(clientThread.getId() , user.getId()));

                Response response = clientThread.getReceiver().getResponse();

                blockUnblockButton.setText("Unblocked");
                blockUnblockButton.setTextFill(Paint.valueOf("white"));
                blockUnblockButton.setStyle("-fx-background-color:#0da5f3;");

            } catch (ResponseNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void chatImageviewPressed(MouseEvent event) {
        //not implemented yet
    }

    @FXML
    void followUnfollowButtonPressed(ActionEvent event) {

        if (followUnfollowButton.getText().equals("Follow")){
            try {
                clientThread.send(new FollowReq(clientThread.getId() , user.getId()));

                Response response = clientThread.getReceiver().getResponse();

                if (!response.isAccepted()){
                    System.out.println(response.getMessage());
                }

                followUnfollowButton.setText("Followed");
                followUnfollowButton.setTextFill(Paint.valueOf("white"));
                followUnfollowButton.setStyle("-fx-background-color:#0da5f3;");

            } catch (ResponseNotFoundException e) {
                e.printStackTrace();
            }

        }
        else {
            try {
                clientThread.send(new UnFollowReq(clientThread.getId() , user.getId()));

                Response response = clientThread.getReceiver().getResponse();

                if (!response.isAccepted()){
                    System.out.println(response.getMessage());
                }

                followUnfollowButton.setText("Unfollowed");
                followUnfollowButton.setTextFill(Paint.valueOf("white"));
                followUnfollowButton.setStyle("-fx-background-color:#0da5f3;");

            } catch (ResponseNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void homeImageviewPressed(MouseEvent event) {
        changeView("home", event);
    }

    @FXML
    void searchImageviewPressed(MouseEvent event) {
        changeView("search", event);
    }

    @FXML
    void userImageviewPressed(MouseEvent event) {
        changeView("profile", event);
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
            clientThread.send(new UserProfileReq(clientThread.getId() , clientThread.getUuid()));

            GetUserProfileRes response = (GetUserProfileRes) clientThread.getReceiver().getResponse();

            user = new User(response.getId(), response.getName() , null , null , null , null , response.getCountry(), null);

            if(!response.isAccepted())
            {
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


            clientThread.send(new MyProfileReq(clientThread.getId()));

            GetUserProfileRes userResponse = (GetUserProfileRes) clientThread.getReceiver().getResponse();

            for (User user: userResponse.getFollowing()) {
                if (user.getId().equals(response.getId())){
                    followUnfollowButton.setText("Unfollow");
                    break;
                }
            }

            for (User user: userResponse.getBlackList()) {
                if (user.getId().equals(response.getId())){
                    blockUnblockButton.setText("Unblock");
                    break;
                }
            }

        } catch(ResponseNotFoundException e) {
            e.printStackTrace();
        }

    }
}

