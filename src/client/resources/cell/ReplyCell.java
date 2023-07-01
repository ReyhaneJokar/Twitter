package client.resources.cell;

import client.ClientThread;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.exception.ResponseNotFoundException;
import model.request.user.TweetInfoReq;
import model.response.GetTweetInfoRes;
import model.tweet.Tweet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

public class ReplyCell extends AnchorPane {
    private final ClientThread clientThread;
    private final Tweet tweet;

    private final Circle circleClipProfile;
    private final Label userNameLabel;
    private final Label userIdLabel;

    private Label userReplyLabel;
    private Label dateLabel;

    private final TextArea textArea;

    public ReplyCell(Tweet tweet) {
        this.clientThread = ClientThread.getClientThread();

        this.tweet = tweet;
        userNameLabel = new Label(tweet.getUser().getName());
        userIdLabel = new Label("@" + tweet.getUser().getId());
        circleClipProfile = new Circle(20);
        circleClipProfile.setStroke(Color.GRAY);
        circleClipProfile.setFill(Color.SNOW);
        if (tweet.getUser().getProfile().getAvatar() != null) {
            InputStream inputStream = new ByteArrayInputStream(tweet.getUser().getProfile().getAvatar());
            circleClipProfile.setFill(new ImagePattern(new Image(inputStream)));
        }
        textArea = new TextArea(tweet.getText());
        dateLabel = new Label(calculateDateDifference(tweet.getDate()));

        try {
            clientThread.send(new TweetInfoReq(clientThread.getId(), clientThread.getUuid()));

            GetTweetInfoRes response = (GetTweetInfoRes) clientThread.getReceiver().getResponse();

            userReplyLabel = new Label("Replying to @" + response.getTweet().getUser().getId());

        } catch (ResponseNotFoundException e) {
            e.printStackTrace();
        }

        this.getChildren().addAll(circleClipProfile , userNameLabel , userIdLabel , userReplyLabel , dateLabel , textArea);

        setConfig();
        setLocation();

    }


    private void setConfig() {
        this.setPrefSize(470, 150);
        this.setMinSize(470, 150);
        this.setMaxSize(470, 150);

        this.setStyle("-fx-background-color:white;" +
                "-fx-background-radius:20;" +
                "-fx-border-radius:20;" +
                "-fx-border-color:gray");

        userNameLabel.setFont(Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 21));
        userNameLabel.setStyle("-fx-text-fill:black;");


        userIdLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 14));
        userIdLabel.setStyle("-fx-text-fill:gray;");


        textArea.setStyle("-fx-background-color:#242424;" +
                "-fx-text-fill:black;");
        textArea.setMaxSize(450.0, 80.0);
        textArea.setMinSize(450.0, 80.0);
        textArea.setFont(Font.font("Roboto", FontWeight.BLACK, 14));
        textArea.setEditable(false);

        userReplyLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 11));
        userReplyLabel.setStyle("-fx-text-fill:gray;");


        dateLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 11));
        dateLabel.setStyle("-fx-text-fill:gray");

    }

    private void setLocation() {

        AnchorPane.setTopAnchor(circleClipProfile, 10.0);
        AnchorPane.setLeftAnchor(circleClipProfile, 10.0);

        AnchorPane.setTopAnchor(userNameLabel, 10.0);
        AnchorPane.setLeftAnchor(userNameLabel, 65.0);

        AnchorPane.setTopAnchor(userIdLabel, 10.0);
        AnchorPane.setLeftAnchor(userIdLabel, 110.0);

        AnchorPane.setTopAnchor(textArea, 60.0);
        AnchorPane.setLeftAnchor(textArea, 10.0);

        AnchorPane.setTopAnchor(dateLabel, 11.0);
        AnchorPane.setLeftAnchor(dateLabel, 225.0);

        AnchorPane.setTopAnchor(userReplyLabel, 39.0);
        AnchorPane.setLeftAnchor(userReplyLabel, 65.0);

    }



    public static String calculateDateDifference(Date myDate) {
        long currentTime = System.currentTimeMillis();
        long diff = currentTime - myDate.getTime();

        long diffMinutes = diff / (60 * 1000);
        if (diffMinutes < 60) {
            return diffMinutes + " minutes";
        }

        long diffHours = diff / (60 * 60 * 1000);
        if (diffHours < 24) {
            return diffHours + "h";
        }

        long diffDays = diff / (24 * 60 * 60 * 1000);
        if (diffDays > 1) {
            // Format the date as "day month"
            String formattedDate = String.format("%1$te %1$tB", myDate);
            return formattedDate;
        }

        return "";


    }

}
