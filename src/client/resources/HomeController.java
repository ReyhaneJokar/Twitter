package client.resources;

import client.resources.cell.TweetCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.exception.ResponseNotFoundException;
import model.request.user.MyProfileReq;
import model.request.user.TimelineReq;
import model.response.GetTimelineRes;
import model.response.GetUserProfileRes;
import model.tweet.Tweet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController extends Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView addTweetImageview;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView chatImageview;

    @FXML
    private ImageView homeImageview;

    @FXML
    private ImageView searchImageview;

    @FXML
    private ImageView userImageview;

    private VBox tweetContent;


    @FXML
    void chatImageviewPressed(MouseEvent event) {
        //not implemented yet
    }

    @FXML
    void searchImageviewPressed(MouseEvent event) {
        changeView("search", event);
    }

    @FXML
    void userImageviewPressed(MouseEvent event) {
        changeView("profile", event).getController();
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

            if(!response.isAccepted())
            {
                System.out.println(response.getMessage());
            }

            if (response.getProfile().getAvatar() != null){
                InputStream inputStream = new ByteArrayInputStream(response.getProfile().getAvatar());
                profilePicCircle.setFill(new ImagePattern(new Image(inputStream)));
            }
        } catch(ResponseNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Tweet> tweets = new ArrayList<>();

        //get tweets from server
        try {
            clientThread.send(new TimelineReq(clientThread.getId()));

            GetTimelineRes response = (GetTimelineRes) clientThread.getReceiver().getResponse();
            tweets = response.getTweets();

            if(!response.isAccepted()) {
                System.out.println(response.getMessage());
            }
        } catch (ResponseNotFoundException e) {
            e.printStackTrace();
        }

        for (Tweet tweet : tweets) {
            TweetCell tweetCell = new TweetCell(tweet);
            tweetContent.getChildren().add(tweetCell);
        }

    }

    @FXML
    void addTweetImageviewPressed(MouseEvent event) {
        changeView("addTweet", event);
    }


}

