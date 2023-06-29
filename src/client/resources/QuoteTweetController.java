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
import model.exception.ResponseNotFoundException;
import model.request.user.MyProfileReq;
import model.response.GetUserProfileRes;

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

    @FXML
    void backImageview(MouseEvent event) {
        changeView("home", event);
    }

    @FXML
    void doneImageviewPressed(MouseEvent event) {

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

        } catch(ResponseNotFoundException e) {
            //closeScene();
        }
    }
}
