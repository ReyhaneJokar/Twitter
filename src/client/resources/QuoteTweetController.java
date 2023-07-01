package client.resources;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Response;
import model.exception.ResponseNotFoundException;
import model.request.tweet.QuoteReq;
import model.request.user.MyProfileReq;
import model.request.user.TweetInfoReq;
import model.response.GetTweetInfoRes;
import model.response.GetUserProfileRes;
import model.tweet.Tweet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class QuoteTweetController extends Controller implements Initializable {

    @FXML
    private ImageView backImageview;

    @FXML
    private ImageView doneImageview;

    @FXML
    private Label errorLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Circle profileCircle;

    @FXML
    private TextArea quoteTextfield;

    @FXML
    private Label targetIdLabel;

    @FXML
    private Label targetNameLabel;

    @FXML
    private TextArea targetTweetTextfield;

    private Tweet tweet;

    @FXML
    void backImageview(MouseEvent event) {
        changeView("home", event);
    }

    @FXML
    void doneImageviewPressed(MouseEvent event) {

        try {
            clientThread.send(new QuoteReq(clientThread.getId(), tweet.getUuid() , tweet.getText() , tweet.getImage() , tweet.getLikes() , tweet.getRetweets() , tweet.getDate() , tweet.getReplies() , quoteTextfield.getText() , tweet.getUser()));

            Response response =  clientThread.getReceiver().getResponse();

            if (response.isAccepted()){
                changeView("home" , event);
            }
        }catch (ResponseNotFoundException e){
            e.printStackTrace();
        }


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

            idLabel.setText("@" + response.getId());
            nameLabel.setText(response.getName());

            if (response.getProfile().getAvatar() != null){
                InputStream inputStream = new ByteArrayInputStream(response.getProfile().getAvatar());
                profileCircle.setFill(new ImagePattern(new Image(inputStream)));
            }

            clientThread.send(new TweetInfoReq(clientThread.getId(), clientThread.getUuid()));

            GetTweetInfoRes quoteResponse = (GetTweetInfoRes) clientThread.getReceiver().getResponse();
            tweet = quoteResponse.getTweet();

            if(!response.isAccepted())
            {
                System.out.println(response.getMessage());
            }

            targetNameLabel.setText(tweet.getUser().getName());
            targetIdLabel.setText("@" + tweet.getUser().getId());
            targetTweetTextfield.setText(tweet.getText());

        } catch(ResponseNotFoundException e) {
            e.printStackTrace();
        }
    }
}
