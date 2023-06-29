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
import javafx.stage.FileChooser;
import model.Response;
import model.exception.ResponseNotFoundException;
import model.request.tweet.TweetReq;
import model.request.user.MyProfileReq;
import model.response.GetUserProfileRes;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AddTweetController extends Controller implements Initializable {

    @FXML
    private ImageView backImageview;

    @FXML
    private ImageView doneImageview;

    @FXML
    private Label idLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Circle profileCircle;

    @FXML
    private ImageView tweetImageview;

    @FXML
    private TextArea tweetTextfield;

    final FileChooser fileChooser = new FileChooser();


    @FXML
    void backImageview(MouseEvent event) {
        changeView("home", event);
    }

    @FXML
    void tweetImageviewPressed(MouseEvent event) {
        fileChooser.setTitle("Choose a picture");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files" , "*.png" , "*.jpg" , "*gif"));

        File file = fileChooser.showOpenDialog(null);


        if (file != null){
            tweetImageview.setImage(new Image(file.toURI().toString()));
        }
        else {
            errorLabel.setText("Invalid file.");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    void doneImageviewPressed(MouseEvent event) {

        try {
            if (tweetImageview.getImage() != null){
                BufferedImage bufferedImage = ImageIO.read((ImageInputStream) tweetImageview.getImage());
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", outputStream);

                clientThread.send(new TweetReq(clientThread.getId(), tweetTextfield.getText() , outputStream.toByteArray(), 0 , 0 , new Date() , null));

            }
            else {
                clientThread.send(new TweetReq(clientThread.getId(), tweetTextfield.getText() , null, 0 , 0 , new Date() , null));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            Response response = clientThread.getReceiver().getResponse();
                if (!response.isAccepted()){
                    errorLabel.setText("something is wrong.");
                    errorLabel.setVisible(true);
                }
            }catch(ResponseNotFoundException e){
                e.printStackTrace();
                errorLabel.setText("No response was received from server");
                errorLabel.setVisible(true);
            }

        changeView("home", event);
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

