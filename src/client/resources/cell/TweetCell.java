package client.resources.cell;

import client.ClientThread;
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
import model.request.tweet.QuoteReq;
import model.request.tweet.RetweetReq;
import model.tweet.Tweet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class TweetCell extends AnchorPane {

    private ClientThread clientThread;

    private Circle circleClipProfile;
    private Label userNameLabel;
    private Label userIdLabel;

    private TextArea textArea ;
    private ImageView tweetImage;
    private Rectangle tweetRectangle;

    private Label dateLabel;
    private Label typeOfAppLabel;
    private Label countOfRetweet ;
    private Label retweetLabel ;
    private Label countOfLikes ;
    private Label likeLabel ;
    private Label countOfReply;
    private Label replyLabel;
    private Label userRetweetedLabel;

    private Separator separator1;
    private Separator separator2;

    private Button quoteButton;
    private Button retweetButton;
    private Button likeButton;

    private Tweet tweet;


    private static Image redHeartImage = new Image(FxClient.class.getResource("pic/heart_red.png").toExternalForm());
    private static Image grayHeartImage = new Image(FxClient.class.getResource("pic/heart_gray.png").toExternalForm());
    private static Image quoteImage = new Image(FxClient.class.getResource("pic/quote.png").toExternalForm());
    private static Image retImage = new Image(FxClient.class.getResource("pic/retweet.png").toExternalForm());
    private static Image blueretweetImage = new Image(FxClient.class.getResource("pic/blueretweet.jpg").toExternalForm());

    private ImageView redHeartImageView = new ImageView(redHeartImage);
    private ImageView grayHeartImageView = new ImageView(grayHeartImage);
    private ImageView quoteImageView = new ImageView(quoteImage);
    private ImageView retImageView = new ImageView(retImage);
    private ImageView blue_retImageView = new ImageView(blueretweetImage);

    public TweetCell(Tweet tweet){
        this.clientThread = ClientThread.getClientThread();

        this.tweet = tweet;
        userNameLabel = new Label(tweet.getUser().getName());
        userIdLabel = new Label("@" + tweet.getUser().getId());
        circleClipProfile = new Circle(20);
        if (tweet.getUser().getProfile().getAvatar() != null){
            InputStream inputStream = new ByteArrayInputStream(tweet.getUser().getProfile().getAvatar());
            circleClipProfile.setFill(new ImagePattern(new Image(inputStream)));
        }
        textArea = new TextArea(tweet.getText());
        if (tweet.getImage() != null){
            InputStream inputStream = new ByteArrayInputStream(tweet.getImage());
            tweetRectangle.setFill(new ImagePattern(new Image(inputStream)));
        }
        else {
            tweetImage = new ImageView(new Image(FxClient.class.getResource("pic/default-tweet.png").toExternalForm()));
        }
        dateLabel = new Label(tweet.getDate().toString());
        typeOfAppLabel = new Label("Twitter for Phone");
        separator1 = new Separator();
        separator2 = new Separator();
        countOfRetweet = new Label(tweet.getRetweets() + "");
        retweetLabel = new Label("Retweets");
        countOfLikes = new Label(tweet.getLikes() + "");
        likeLabel = new Label("Likes");
        countOfReply = new Label(tweet.getReplies().size() + "");
        replyLabel = new Label("Replies");
        quoteButton = new Button();
        retweetButton = new Button();
        likeButton = new Button();
        userRetweetedLabel = new Label();

        circleClipProfile.setStroke(Color.GRAY);
        circleClipProfile.setFill(Color.SNOW);
        if (tweet.getUser().getProfile().getAvatar() != null){
            InputStream inputStream = new ByteArrayInputStream(tweet.getUser().getProfile().getAvatar());
            circleClipProfile.setFill(new ImagePattern(new Image(inputStream)));
        }
        else {
            circleClipProfile.setStroke(Color.GRAY);
            circleClipProfile.setFill(Color.SNOW);
        }

        this.getChildren().addAll(circleClipProfile , userNameLabel , userIdLabel, textArea , tweetImage ,dateLabel ,typeOfAppLabel ,countOfRetweet , countOfLikes , countOfReply , replyLabel , retweetLabel , likeLabel , quoteButton , retweetButton , likeButton  ,separator1,separator2);

        setConfig();
        setLocation();
        setAction();
    }


    private void setConfig(){
        this.setPrefSize(470,430);
        this.setMinSize(470,430);
        this.setMaxSize(470,430);

        this.setStyle("-fx-background-color:white;" +
                "-fx-background-radius:20;" +
                "-fx-border-radius:20;" +
                "-fx-border-color:gray");

        userNameLabel.setFont(Font.font("Arial Rounded MT Bold", FontWeight.BOLD,21));
        userNameLabel.setStyle("-fx-text-fill:black;");

        userIdLabel.setFont(Font.font("Roboto", FontWeight.NORMAL,14));
        //userIdLabel.setStyle("-fx-text-fill:gray;");

        textArea.setStyle("-fx-background-color:#242424;" +
                "-fx-text-fill:black;");
        textArea.setMaxSize(450.0,120.0);
        textArea.setMinSize(450.0,120.0);
        textArea.setFont(Font.font("Roboto",FontWeight.BLACK,14));
        textArea.setEditable(false);

        tweetImage.setFitHeight(120);
        tweetImage.setFitWidth(450);
        tweetRectangle = new Rectangle(450,120);
        tweetRectangle.setArcHeight(20.0);
        tweetRectangle.setArcWidth(20.0);
        tweetImage.setClip(tweetRectangle);

        dateLabel.setFont(Font.font("Roboto", FontWeight.NORMAL,11));
        dateLabel.setStyle("-fx-text-fill:gray");

        typeOfAppLabel.setFont(Font.font("Roboto", FontWeight.NORMAL,11));
        typeOfAppLabel.setStyle("-fx-text-fill:#3366CC");

        separator1.setPrefSize(470.0,1.0);
        separator1.setStyle("-fx-background-color:#858080;");
        separator1.setOpacity(0.15);

        separator2.setPrefSize(470.0,1.0);
        separator2.setStyle("-fx-background-color:#858080;");
        separator2.setOpacity(0.15);

        countOfRetweet.setFont(Font.font("Roboto-Bold", FontWeight.NORMAL,13));
        countOfRetweet.setStyle("-fx-text-fill:black;");

        retweetLabel.setFont(Font.font("Roboto", FontWeight.NORMAL,13));
        retweetLabel.setStyle("-fx-text-fill:gray;");

        countOfLikes.setFont(Font.font("Roboto-Bold", FontWeight.NORMAL,13));
        countOfLikes.setStyle("-fx-text-fill:black;");

        likeLabel.setFont(Font.font("Roboto", FontWeight.NORMAL,13));
        likeLabel.setStyle("-fx-text-fill:gray;");

        countOfReply.setFont(Font.font("Roboto-Bold", FontWeight.NORMAL,13));
        countOfReply.setStyle("-fx-text-fill:black;");

        replyLabel.setFont(Font.font("Roboto", FontWeight.NORMAL,13));
        replyLabel.setStyle("-fx-text-fill:gray;");

        userRetweetedLabel.setStyle("-fx-text-fill:red;");


        retImageView.setFitWidth(20);
        retImageView.setFitHeight(20);
        blue_retImageView.setFitHeight(20);
        blue_retImageView.setFitWidth(20);
        retweetButton.setGraphic(retImageView);
        retweetButton.setPrefSize(20 , 20);
        retweetButton.setStyle("-fx-background-color:white;");

        quoteImageView.setFitWidth(20);
        quoteImageView.setFitHeight(20);
        quoteButton.setGraphic(quoteImageView);
        quoteButton.setPrefSize(20 , 20);
        quoteButton.setStyle("-fx-background-color:white;");

        grayHeartImageView.setFitWidth(20);
        grayHeartImageView.setFitHeight(20);
        redHeartImageView.setFitWidth(20);
        redHeartImageView.setFitHeight(20);
        likeButton.setGraphic(grayHeartImageView);
        likeButton.setPrefSize(20 , 20);
        likeButton.setStyle("-fx-background-color:white;");


    }

    private void setLocation(){

        AnchorPane.setTopAnchor(circleClipProfile,10.0);
        AnchorPane.setLeftAnchor(circleClipProfile,10.0);

        AnchorPane.setTopAnchor(userNameLabel,11.0);
        AnchorPane.setLeftAnchor(userNameLabel,65.0);

        AnchorPane.setTopAnchor(userIdLabel,39.0);
        AnchorPane.setLeftAnchor(userIdLabel,65.0);

        //
        AnchorPane.setTopAnchor(userRetweetedLabel,11.0);
        AnchorPane.setRightAnchor(userRetweetedLabel,11.0);

        //

        AnchorPane.setTopAnchor(textArea,60.0);
        AnchorPane.setLeftAnchor(textArea,10.0);

        AnchorPane.setTopAnchor(tweetImage,190.0);
        AnchorPane.setLeftAnchor(tweetImage,10.0);

        AnchorPane.setTopAnchor(dateLabel,310.0);
        AnchorPane.setLeftAnchor(dateLabel,30.0);
        AnchorPane.setTopAnchor(typeOfAppLabel,310.0);
        AnchorPane.setLeftAnchor(typeOfAppLabel,250.0);


        AnchorPane.setTopAnchor(separator1,330.0);
        AnchorPane.setLeftAnchor(separator1,0.0);

        AnchorPane.setTopAnchor(separator2,370.0);
        AnchorPane.setLeftAnchor(separator2,0.0);

        AnchorPane.setTopAnchor(countOfRetweet,342.0);
        AnchorPane.setLeftAnchor(countOfRetweet,75.0);

        AnchorPane.setTopAnchor(retweetLabel,342.0);
        AnchorPane.setLeftAnchor(retweetLabel,85.0);

        AnchorPane.setTopAnchor(countOfLikes,342.0);
        AnchorPane.setLeftAnchor(countOfLikes,200.0);

        AnchorPane.setTopAnchor(likeLabel,342.0);
        AnchorPane.setLeftAnchor(likeLabel,210.0);


        AnchorPane.setTopAnchor(countOfReply,342.0);
        AnchorPane.setLeftAnchor(countOfReply,325.0);

        AnchorPane.setTopAnchor(replyLabel,342.0);
        AnchorPane.setLeftAnchor(replyLabel,335.0);


        AnchorPane.setBottomAnchor(quoteButton,15.0);
        AnchorPane.setLeftAnchor(quoteButton,85.0);

        AnchorPane.setBottomAnchor(retweetButton,15.0);
        AnchorPane.setLeftAnchor(retweetButton,210.0);

        AnchorPane.setBottomAnchor(likeButton,15.0);
        AnchorPane.setLeftAnchor(likeButton,335.0);


    }

    private void setAction(){
        quoteButton.setOnAction( actionEvent -> {

            clientThread.send(new QuoteReq(clientThread.getId(),tweet.getUuid() ,  tweet.getText() , tweet.getImage() , tweet.getLikes() , tweet.getRetweets() , tweet.getDate() , tweet.getReplies() , "" , null));
            try {
                Response response = clientThread.getReceiver().getResponse();

                if(response.isAccepted()) {

                    countOfRetweet.setText((Integer.parseInt(countOfRetweet.getText()) + 1 ) + "");
                }
                else{
                    throw new TweetNotFoundException();
                }

            } catch (TweetNotFoundException | ResponseNotFoundException e) {
                e.printStackTrace();
            }
        });


        retweetButton.setOnAction( actionEvent -> {

            clientThread.send(new RetweetReq(clientThread.getId() ,tweet.getUuid() , tweet.getText() , tweet.getImage() , tweet.getLikes() , tweet.getRetweets() , tweet.getDate() , tweet.getReplies() , tweet.getUser()));

            try {
                Response response = clientThread.getReceiver().getResponse();

                if(response.isAccepted()) {
                    countOfRetweet.setText((Integer.parseInt(countOfRetweet.getText()) + 1 ) + "");
                    retweetButton.setGraphic(blue_retImageView);
                    userRetweetedLabel.setText("You Retweeted!");
                    userRetweetedLabel.setVisible(true);
                }
                else{
                    throw new TweetNotFoundException();
                }

            } catch (TweetNotFoundException | ResponseNotFoundException e) {
                e.printStackTrace();
            }
        });

        likeButton.setOnAction( actionEvent -> {

            clientThread.send(new LikeTweetReq(clientThread.getId() ,tweet.getUuid() , tweet.getText() , tweet.getImage() , tweet.getLikes() , tweet.getRetweets() , tweet.getDate() , tweet.getReplies() , null));

            try {
                Response response = clientThread.getReceiver().getResponse();

                if(response.isAccepted()) {

                    countOfLikes.setText((Integer.parseInt(countOfLikes.getText()) + 1 ) + "");
                    likeButton.setGraphic(redHeartImageView);
                }
                else{
                    throw new TweetNotFoundException();
                }

            } catch (TweetNotFoundException | ResponseNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

}
