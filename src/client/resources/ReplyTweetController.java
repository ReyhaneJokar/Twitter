package client.resources;

import client.resources.cell.ReplyCell;
import client.resources.cell.TweetCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Response;
import model.exception.ResponseNotFoundException;
import model.request.tweet.ReplyReq;
import model.request.user.TweetInfoReq;
import model.response.GetTweetInfoRes;
import model.tweet.Tweet;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

public class ReplyTweetController extends Controller implements Initializable {

    @FXML
    private ImageView backImageview;

    @FXML
    private TextField replyTextfield;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView sendImageview;

    private VBox tweetContent;

    private Tweet targetTweet;

    @FXML
    void backImageviewPressed(MouseEvent event) {
        changeView("home", event);
    }

    @FXML
    void sendImageviewPressed(MouseEvent event) {
        if (replyTextfield.getText() != null){

            clientThread.send(new ReplyReq(clientThread.getId(), targetTweet.getUuid() , targetTweet.getText() , targetTweet.getImage() , targetTweet.getLikes() , targetTweet.getRetweets() , targetTweet.getDate() , targetTweet.getReplies() , UUID.randomUUID() , targetTweet.getUser().getId() , replyTextfield.getText(), new Date() , null));

            try {
                Response response = clientThread.getReceiver().getResponse();

                if (response.isAccepted()){
                    replyTextfield.setText("Tweet replied!");
                    replyTextfield.setStyle("-fx-text-fill:#0da5f3;");
                }

            } catch (ResponseNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tweetContent = new VBox();
        scrollPane.setContent(tweetContent);
        tweetContent.setSpacing(10);
        tweetContent.setStyle("-fx-background-color:white;" + "-fx-padding: 8;");

        try {
            clientThread.send(new TweetInfoReq(clientThread.getId(), clientThread.getUuid()));

            GetTweetInfoRes response = (GetTweetInfoRes) clientThread.getReceiver().getResponse();
            targetTweet = response.getTweet();

            TweetCell tweetCell = new TweetCell(response.getTweet());
            tweetContent.getChildren().add(tweetCell);

            for (Tweet tweet: response.getTweet().getReplies()) {
                ReplyCell replyCell = new ReplyCell(tweet);
                tweetContent.getChildren().add(replyCell);
            }

        } catch (ResponseNotFoundException e) {
            e.printStackTrace();
        }

    }
}

