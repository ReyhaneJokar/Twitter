package client.resources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import model.Response;
import model.exception.ResponseNotFoundException;
import model.request.user.AvatarReq;
import model.request.user.BioReq;
import model.request.user.HeaderReq;
import model.request.user.MyProfileReq;
import model.response.GetUserProfileRes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController extends Controller implements Initializable {

    @FXML
    private TextField bioTextfield;

    @FXML
    private Label dateLabel;

    @FXML
    private Button doneButton;

    @FXML
    private Button editHeaderButton;

    @FXML
    private Button editProfilePicButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView headerImageview;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Circle profileCircle;

    @FXML
    private TextField websiteTextfield;

    @FXML
    private TextField locationTextfield;

    final FileChooser fileChooser = new FileChooser();

    @FXML
    void doneButtonPressed(ActionEvent event) {
        clientThread.send(new BioReq(clientThread.getId() , bioTextfield.getText() , websiteTextfield.getText() , locationTextfield.getText()));
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
        changeView("profile", event);
    }

    @FXML
    void editHeaderButtonPressed(ActionEvent event) {
        File file = openFileChooser(fileChooser);

        if (file != null){
            headerImageview.setImage(new Image(file.toURI().toString()));

            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", outputStream);
                clientThread.send(new HeaderReq(clientThread.getId(), outputStream.toByteArray()));

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
        }
        else {
            errorLabel.setText("Invalid file.");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    void editProfilePicButtonPressed(ActionEvent event) {
        File file = openFileChooser(fileChooser);

        if (file != null){
            Image image = new Image(file.toURI().toString());
            profileCircle.setFill(new ImagePattern(image));

            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", outputStream);
                clientThread.send(new AvatarReq(clientThread.getId(), outputStream.toByteArray()));

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
        }
        else {
            errorLabel.setText("Invalid file.");
            errorLabel.setVisible(true);
        }
    }

    private File openFileChooser(FileChooser fileChooser){
        fileChooser.setTitle("Choose a picture");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files" , "*.png" , "*.jpg" , "*gif"));

        File file = fileChooser.showOpenDialog(null);

        return file;
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
            bioTextfield.setText(response.getProfile().getBio());
            locationTextfield.setText(response.getProfile().getLocation());
            if (response.getProfile().getWebsite() != null){
                websiteTextfield.setText(response.getProfile().getWebsite());
            }
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

        } catch(ResponseNotFoundException e) {
            //closeScene();
        }
    }
}

