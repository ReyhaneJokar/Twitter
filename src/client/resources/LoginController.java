package client.resources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Response;
import model.exception.ResponseNotFoundException;
import model.request.Authentication.LogInReq;

public class LoginController extends Controller{

    @FXML
    private Label errorTextfield;

    @FXML
    private TextField idTextfield;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordTextfield;

    @FXML
    private Button signupButton;

    @FXML
    void loginButtonPressed(ActionEvent event) {
        clientThread.setId(idTextfield.getText());
        clientThread.send(new LogInReq(clientThread.getId(), passwordTextfield.getText(), null));

        try{
            Response response = clientThread.getReceiver().getResponse();
            if(response.isAccepted()){
                changeView("home", event);
            }
            else{
                errorTextfield.setVisible(true);
                errorTextfield.setText("Not found.");
            }
        }
        catch(ResponseNotFoundException e){
            e.printStackTrace();
            errorTextfield.setText("No response was received from server");
        }
    }

    @FXML
    void signupButtonPressed(ActionEvent event) {
        changeView("signup", event);
    }


}

