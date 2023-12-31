package client.resources.cell;

import client.ClientThread;
import client.resources.Controller;
import client.resources.FxClient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Response;
import model.exception.ResponseNotFoundException;
import model.exception.TweetNotFoundException;
import model.request.tweet.LikeTweetReq;
import model.request.tweet.RetweetReq;
import model.tweet.Tweet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;


public class TweetCell extends AnchorPane {

    private final ClientThread clientThread;

    private final Circle circleClipProfile;
    private final Button userNameLabel;
    private final Label userIdLabel;

    private final TextArea textArea;
    private final ImageView tweetImage;
    private Rectangle tweetRectangle;

    private final Label dateLabel;
    private final Label typeOfAppLabel;
    private Label countOfRetweet;
    private final Label retweetLabel;
    private Label countOfLikes;
    private final Label likeLabel;
    private Label countOfReply;
    private final Button replyLabel;
    private final Label userRetweetedLabel;

    private final Separator separator1;
    private final Separator separator2;

    private final Button quoteButton;
    private final Button retweetButton;
    private final Button likeButton;

    private final Tweet tweet;


    private static final Image redHeartImage = new Image(FxClient.class.getResource("pic/heart_red.png").toExternalForm());
    private static final Image grayHeartImage = new Image(FxClient.class.getResource("pic/heart_gray.png").toExternalForm());
    private static final Image quoteImage = new Image(FxClient.class.getResource("pic/quote.png").toExternalForm());
    private static final Image retImage = new Image(FxClient.class.getResource("pic/retweet.png").toExternalForm());
    private static final Image blueretweetImage = new Image(FxClient.class.getResource("pic/blueretweet.jpg").toExternalForm());

    private final ImageView redHeartImageView = new ImageView(redHeartImage);
    private final ImageView grayHeartImageView = new ImageView(grayHeartImage);
    private final ImageView quoteImageView = new ImageView(quoteImage);
    private final ImageView retImageView = new ImageView(retImage);
    private final ImageView blue_retImageView = new ImageView(blueretweetImage);

    public TweetCell(Tweet tweet) {
        this.clientThread = ClientThread.getClientThread();

        this.tweet = tweet;
        userNameLabel = new Button(tweet.getUser().getName());
        userIdLabel = new Label("@" + tweet.getUser().getId());
        circleClipProfile = new Circle(20);
        if (tweet.getUser().getProfile().getAvatar() != null) {
            InputStream inputStream = new ByteArrayInputStream(tweet.getUser().getProfile().getAvatar());
            circleClipProfile.setFill(new ImagePattern(new Image(inputStream)));
        }
        textArea = new TextArea(tweet.getText());
        if (tweet.getImage() != null) {
            InputStream inputStream = new ByteArrayInputStream(tweet.getImage());
//            tweetRectangle.setFill(new ImagePattern(new Image(inputStream)));
            tweetImage = new ImageView(new Image(inputStream));
        } else {
            tweetImage = new ImageView(new Image(FxClient.class.getResource("pic/default-tweet.png").toExternalForm()));
        }
        dateLabel = new Label(calculateDateDifference(tweet.getDate()));
        typeOfAppLabel = new Label("Twitter for Phone");
        separator1 = new Separator();
        separator2 = new Separator();
        countOfRetweet = new Label(tweet.getRetweets() + "");
        retweetLabel = new Label("Retweets");
        countOfLikes = new Label(tweet.getLikes() + "");
        likeLabel = new Label("Likes");
        countOfReply = new Label(tweet.getReplies().size() + "");
        replyLabel = new Button("Replies");
        quoteButton = new Button();
        retweetButton = new Button();
        likeButton = new Button();
        userRetweetedLabel = new Label();

        circleClipProfile.setStroke(Color.GRAY);
        circleClipProfile.setFill(Color.SNOW);
        if (tweet.getUser().getProfile().getAvatar() != null) {
            InputStream inputStream = new ByteArrayInputStream(tweet.getUser().getProfile().getAvatar());
            circleClipProfile.setFill(new ImagePattern(new Image(inputStream)));
        } else {
            circleClipProfile.setStroke(Color.GRAY);
            circleClipProfile.setFill(Color.SNOW);
        }

        this.getChildren().addAll(userRetweetedLabel, circleClipProfile, userNameLabel, userIdLabel, textArea, tweetImage, dateLabel, typeOfAppLabel, countOfRetweet, countOfLikes, countOfReply, replyLabel, retweetLabel, likeLabel, quoteButton, retweetButton, likeButton, separator1, separator2);

        setConfig();
        setLocation();
        setAction();
    }


    private void setConfig() {
        this.setPrefSize(470, 430);
        this.setMinSize(470, 430);
        this.setMaxSize(470, 430);

        this.setStyle("-fx-background-color:white;" +
                "-fx-background-radius:20;" +
                "-fx-border-radius:20;" +
                "-fx-border-color:gray");

        userNameLabel.setFont(Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 21));
        userNameLabel.setStyle("-fx-text-fill:black;");
        userNameLabel.setStyle("-fx-background-color:white;" +"-fx-border-color:white");


        userIdLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 14));
        userIdLabel.setStyle("-fx-text-fill:gray;");


        textArea.setStyle("-fx-background-color:#242424;" +
                "-fx-text-fill:black;");
        textArea.setMaxSize(450.0, 120.0);
        textArea.setMinSize(450.0, 120.0);
        textArea.setFont(Font.font("Roboto", FontWeight.BLACK, 14));
        textArea.setEditable(false);

        tweetImage.setFitHeight(120);
        tweetImage.setFitWidth(450);
        tweetRectangle = new Rectangle(450, 120);
        tweetRectangle.setArcHeight(20.0);
        tweetRectangle.setArcWidth(20.0);
        tweetImage.setClip(tweetRectangle);
        tweetImage.setPreserveRatio(true);

        dateLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 11));
        dateLabel.setStyle("-fx-text-fill:gray");

        typeOfAppLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 11));
        typeOfAppLabel.setStyle("-fx-text-fill:#3366CC");

        separator1.setPrefSize(470.0, 1.0);
        separator1.setStyle("-fx-background-color:#858080;");
        separator1.setOpacity(0.15);

        separator2.setPrefSize(470.0, 1.0);
        separator2.setStyle("-fx-background-color:#858080;");
        separator2.setOpacity(0.15);

        countOfRetweet.setFont(Font.font("Roboto-Bold", FontWeight.NORMAL, 13));
        countOfRetweet.setStyle("-fx-text-fill:black;");

        retweetLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 13));
        retweetLabel.setStyle("-fx-text-fill:gray;");

        countOfLikes.setFont(Font.font("Roboto-Bold", FontWeight.NORMAL, 13));
        countOfLikes.setStyle("-fx-text-fill:black;");

        likeLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 13));
        likeLabel.setStyle("-fx-text-fill:gray;");

        countOfReply.setFont(Font.font("Roboto-Bold", FontWeight.NORMAL, 13));
        countOfReply.setStyle("-fx-text-fill:black;");

        replyLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 13));
        replyLabel.setStyle("-fx-text-fill:gray;");
        replyLabel.setStyle("-fx-background-color:white;" +"-fx-border-color:white");


        userRetweetedLabel.setStyle("-fx-text-fill:#0da5f3;");


        retImageView.setFitWidth(20);
        retImageView.setFitHeight(20);
        blue_retImageView.setFitHeight(20);
        blue_retImageView.setFitWidth(20);
        retweetButton.setGraphic(retImageView);
        retweetButton.setPrefSize(20, 20);
        retweetButton.setStyle("-fx-background-color:white;");

        quoteImageView.setFitWidth(20);
        quoteImageView.setFitHeight(20);
        quoteButton.setGraphic(quoteImageView);
        quoteButton.setPrefSize(20, 20);
        quoteButton.setStyle("-fx-background-color:white;");

        grayHeartImageView.setFitWidth(20);
        grayHeartImageView.setFitHeight(20);
        redHeartImageView.setFitWidth(20);
        redHeartImageView.setFitHeight(20);
        likeButton.setGraphic(grayHeartImageView);
        likeButton.setPrefSize(20, 20);
        likeButton.setStyle("-fx-background-color:white;");


    }

    private void setLocation() {

        AnchorPane.setTopAnchor(circleClipProfile, 10.0);
        AnchorPane.setLeftAnchor(circleClipProfile, 10.0);

        AnchorPane.setTopAnchor(userNameLabel, 8.0);
        AnchorPane.setLeftAnchor(userNameLabel, 54.0);

        AnchorPane.setTopAnchor(userIdLabel, 39.0);
        AnchorPane.setLeftAnchor(userIdLabel, 65.0);

        AnchorPane.setTopAnchor(userRetweetedLabel, 11.0);
        AnchorPane.setRightAnchor(userRetweetedLabel, 11.0);

        AnchorPane.setTopAnchor(textArea, 60.0);
        AnchorPane.setLeftAnchor(textArea, 10.0);

        AnchorPane.setTopAnchor(tweetImage, 190.0);
        AnchorPane.setLeftAnchor(tweetImage, 10.0);

        AnchorPane.setTopAnchor(dateLabel, 310.0);
//        AnchorPane.setLeftAnchor(dateLabel, 30.0);
        AnchorPane.setLeftAnchor(dateLabel, 75.0);

        AnchorPane.setTopAnchor(typeOfAppLabel, 310.0);
        AnchorPane.setLeftAnchor(typeOfAppLabel, 250.0);


        AnchorPane.setTopAnchor(separator1, 330.0);
        AnchorPane.setLeftAnchor(separator1, 0.0);

        AnchorPane.setTopAnchor(separator2, 370.0);
        AnchorPane.setLeftAnchor(separator2, 0.0);

        AnchorPane.setTopAnchor(countOfRetweet, 342.0);
        AnchorPane.setLeftAnchor(countOfRetweet, 70.0);

        AnchorPane.setTopAnchor(retweetLabel, 342.0);
        AnchorPane.setLeftAnchor(retweetLabel, 85.0);

        AnchorPane.setTopAnchor(countOfLikes, 342.0);
        AnchorPane.setLeftAnchor(countOfLikes, 195.0);

        AnchorPane.setTopAnchor(likeLabel, 342.0);
        AnchorPane.setLeftAnchor(likeLabel, 210.0);


        AnchorPane.setTopAnchor(countOfReply, 342.0);
        AnchorPane.setLeftAnchor(countOfReply, 325.0);

        AnchorPane.setTopAnchor(replyLabel, 337.0);
        AnchorPane.setLeftAnchor(replyLabel, 335.0);


        AnchorPane.setBottomAnchor(quoteButton, 15.0);
        AnchorPane.setLeftAnchor(quoteButton, 85.0);

        AnchorPane.setBottomAnchor(retweetButton, 15.0);
        AnchorPane.setLeftAnchor(retweetButton, 210.0);

        AnchorPane.setBottomAnchor(likeButton, 15.0);
        AnchorPane.setLeftAnchor(likeButton, 335.0);


    }

    private void setAction() {


        quoteButton.setOnAction(actionEvent -> {

            this.clientThread.setUuid(this.tweet.getUuid());

            Controller.changeView("QuoteTweet", actionEvent);

        });


        retweetButton.setOnAction(actionEvent -> {

            clientThread.send(new RetweetReq(clientThread.getId(), tweet.getUuid(), tweet.getText(), tweet.getImage(), tweet.getLikes(), tweet.getRetweets(), tweet.getDate(), tweet.getReplies(), tweet.getUser()));

            try {
                Response response = clientThread.getReceiver().getResponse();

                if (response.isAccepted()) {
                    countOfRetweet.setText((Integer.parseInt(countOfRetweet.getText()) + 1) + "");
                    retweetButton.setGraphic(blue_retImageView);
                    userRetweetedLabel.setText("You Retweeted!");
                    userRetweetedLabel.setVisible(true);
                } else {
                    throw new TweetNotFoundException();
                }

            } catch (TweetNotFoundException | ResponseNotFoundException e) {
                e.printStackTrace();
            }
        });


        likeButton.setOnAction(actionEvent -> {

            clientThread.send(new LikeTweetReq(clientThread.getId(), tweet.getUuid(), tweet.getText(), tweet.getImage(), tweet.getLikes(), tweet.getRetweets(), tweet.getDate(), tweet.getReplies(), tweet.getUser()));

            try {
                Response response = clientThread.getReceiver().getResponse();

                if (response.isAccepted()) {

                    countOfLikes.setText((Integer.parseInt(countOfLikes.getText()) + 1) + "");
                    likeButton.setGraphic(redHeartImageView);
                } else {
                    throw new TweetNotFoundException();
                }

            } catch (TweetNotFoundException | ResponseNotFoundException e) {
                e.printStackTrace();
            }
        });


        userNameLabel.setOnAction(actionEvent -> {
            this.clientThread.setUuid(this.tweet.getUuid());

            Controller.changeView("otherUsersProfile" , actionEvent);

        });


        replyLabel.setOnAction(actionEvent -> {
            this.clientThread.setUuid(this.tweet.getUuid());

            Controller.changeView("replyTweet" , actionEvent);

        });

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



